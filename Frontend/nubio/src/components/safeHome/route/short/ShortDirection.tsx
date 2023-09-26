import axios from "axios";
import { ShortDirectionButton } from "../../../../styles/SKakaoMap";
import { useSelector } from "react-redux";
import proj4 from "proj4";

interface ShortDirectionProps {
  clearRoute: () => void;
}
interface StartCoordinates {
  x: number;
  y: number;
}
interface EndCoordinates {
  x: number;
  y: number;
}

const ShortDirection = ({ clearRoute }: ShortDirectionProps) => {
  const start = useSelector(
    (state: { map: { start: StartCoordinates } }) => state.map.start,
  );
  const end = useSelector(
    (state: { map: { end: EndCoordinates } }) => state.map.end,
  );
  const startName = useSelector(
    (state: { map: { startName: string } }) => state.map.startName,
  );
  const endName = useSelector(
    (state: { map: { endName: string } }) => state.map.endName,
  );
  // 최단거리 길찾기
  const getShortDirection = () => {
    clearRoute();
    var headers = { appKey: "prZbuvPsM53ADwzJMIxl13StkVuNvAG86O6n4YhF" };
    var data = {
      startX: start.x,
      startY: start.y,
      endX: end.x,
      endY: end.y,
      reqCoordType: "WGS84GEO",
      resCoordType: "EPSG3857",
      startName,
      endName,
    };

    axios
      .post(
        "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
        data,
        { headers: headers },
      )
      .then((res) => {
        function flattenArray(arr: any) {
          return arr.reduce((acc: any, val: any) => {
            if (Array.isArray(val)) {
              acc.push(...flattenArray(val));
            } else {
              acc.push(val);
            }
            return acc;
          }, []);
        }
        const coordinates = res.data.features.flatMap((feature: any) =>
          flattenArray(feature.geometry.coordinates),
        );
        const coordinatesList = [];
        for (let i = 0; i < coordinates.length; i += 2) {
          if (i + 1 < coordinates.length) {
            coordinatesList.push([coordinates[i], coordinates[i + 1]]);
          }
        }
        const convertedCoordinatesList = coordinatesList.map((coord: any) => {
          const fromProjection = "EPSG:3857";
          const toProjection = "EPSG:4326";
          const convertedCoord = proj4(fromProjection, toProjection, coord);
          return convertedCoord;
        });
        const coordinate = convertedCoordinatesList.flatMap((feature: any) => {
          return feature;
        });
        const linePath: any = [];
        for (let i = 0; i < coordinate?.length; i += 2) {
          const latitude = parseFloat(coordinate[i + 1]);
          const longitude = parseFloat(coordinate[i]);
          const latLng = new kakao.maps.LatLng(latitude, longitude);
          linePath.push(latLng);
        }

        // // 거리계산 공식
        // const calculateLineDistance = (line: any) => {
        //   const R = 6371;
        //   let totalDistance = 0;
        //   for (let i = 0; i < line.length - 1; i++) {
        //     const point1 = line[i];
        //     const point2 = line[i + 1];
        //     const dLat = deg2rad(point1["Ma"] - point2["Ma"]);
        //     const dLon = deg2rad(point1["La"] - point2["La"]);
        //     if (dLat === 0 && dLon === 0) {
        //       continue;
        //     }
        //     const a =
        //       Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        //       Math.cos(deg2rad(point1["Ma"])) *
        //         Math.cos(deg2rad(point2["Ma"])) *
        //         Math.sin(dLon / 2) *
        //         Math.sin(dLon / 2);

        //     const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        //     const distance = R * c * 1000;
        //     totalDistance += distance;
        //   }

        //   return totalDistance;
        // };
        // // 거리가 계산된 결과 출력 함수
        // const calculateAndDisplayLineDistances = () => {
        //   if (linePath.length > 0) {
        //     const distances: any = calculateLineDistance(linePath);
        //     const walkTime = (distances / 67) | 0;
        //     dispatch(
        //       setShortTime({
        //         time: walkTime,
        //         type: "분",
        //         dis: Math.floor(distances),
        //       }),
        //     );
        //   }
        // };
        // calculateAndDisplayLineDistances();

        var polyline = new kakao.maps.Polyline({
          path: linePath,
          strokeWeight: 5,
          strokeColor: "red",
          strokeOpacity: 0.7,
          strokeStyle: "solid",
        });
        window.polyline = polyline;
        polyline.setMap(window.map);
        // setFindRouteOpen(true);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return <ShortDirectionButton>빠른 길 찾기</ShortDirectionButton>;
};
export default ShortDirection;
