import axios from "axios";
import proj4 from "proj4";
import { useDispatch, useSelector } from "react-redux";
import { setSafeTime, setSafePlace } from "../../../../redux/slice/MapSlice";
import { SafeDirectionButton } from "../../../../styles/SKakaoMap";

interface SafeDirectionProps {
  clearRoute: () => void;
  setFindRouteOpen: (findRouteOpen: boolean) => void;
}
interface StartCoordinates {
  x: number;
  y: number;
}
interface EndCoordinates {
  x: number;
  y: number;
}
const SafeDirection = ({
  clearRoute,
  setFindRouteOpen,
}: SafeDirectionProps) => {
  const deg2rad = (deg: any) => {
    return deg * (Math.PI / 180);
  };
  const dispatch = useDispatch();
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

  // 안전경로 길찾기
  const getSafeLocation = () => {
    axios
      .post("https://nubi0.com/safe/v1/safe/recommend/node", {
        start_location: {
          longitude: start.x,
          latitude: start.y,
        },
        end_location: {
          longitude: end.x,
          latitude: end.y,
        },
      })
      .then((res) => {
        console.log(res);
        if (res.data.data.content.length > 0) {
          var safeLatitude = res.data.data.content[0].location.latitude;
          var safeLongitude = res.data.data.content[0].location.longitude;
          var safePlaces = res.data.data.content[0].safety_facilities;
          dispatch(setSafePlace(safePlaces));
          if (safePlaces.length > 1) {
            for (let i = 0; i < safePlaces.length; i++) {
              let placeName;
              if (safePlaces[i].facility_type == "CONVENIENCE_STORE") {
                placeName = "편의점";
              }
              if (safePlaces[i].facility_type == "POLICE") {
                placeName = "경찰서";
              }
              if (safePlaces[i].facility_type == "LAMP") {
                placeName = "가로등";
              }
              if (safePlaces[i].facility_type == "SAFETY_BELL") {
                placeName = "안전벨";
              }
              let content = `<div class ="label"  style="background:#33ff57; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
            ${placeName}</span><span class="right"></span></div>`;
              let markerPosition = new window.kakao.maps.LatLng(
                safePlaces[i].location.latitude,
                safePlaces[i].location.longitude,
              );
              let customOverlay = new window.kakao.maps.CustomOverlay({
                position: markerPosition,
                content: content,
              });
              window.safeCustomOverlay = customOverlay;
              window.safeCustomOverlay.setMap(window.map);
            }
          }
        }
        getSafeDirection(safeLatitude, safeLongitude);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const getSafeDirection = (safeLatitude: any, safeLongitude: any) => {
    clearRoute();
    var headers = { appKey: "prZbuvPsM53ADwzJMIxl13StkVuNvAG86O6n4YhF" };
    var safeLatituded = safeLatitude?.toString();
    var safeLongituded = safeLongitude?.toString();
    var data = {
      startX: start.x,
      startY: start.y,
      endX: end.x,
      endY: end.y,
      reqCoordType: "WGS84GEO",
      resCoordType: "EPSG3857",
      startName,
      endName,
      passList: safeLongituded + "," + safeLatituded,
      searchOption: 0,
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

        // 거리계산 공식
        const calculateLineDistance = (line: any) => {
          const R = 6371;
          let totalDistance = 0;
          for (let i = 0; i < line.length - 1; i++) {
            const point1 = line[i];
            const point2 = line[i + 1];
            const dLat = deg2rad(point1["Ma"] - point2["Ma"]);
            const dLon = deg2rad(point1["La"] - point2["La"]);
            if (dLat === 0 && dLon === 0) {
              continue;
            }
            const a =
              Math.sin(dLat / 2) * Math.sin(dLat / 2) +
              Math.cos(deg2rad(point1["Ma"])) *
                Math.cos(deg2rad(point2["Ma"])) *
                Math.sin(dLon / 2) *
                Math.sin(dLon / 2);

            const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            const distance = R * c * 1000;
            totalDistance += distance;
          }

          return totalDistance;
        };
        // 거리가 계산된 결과 출력 함수
        const calculateAndDisplayLineDistances = () => {
          if (linePath.length > 0) {
            const distances: any = calculateLineDistance(linePath);
            const walkTime = (distances / 67) | 0;
            dispatch(
              setSafeTime({
                time: walkTime,
                type: "분",
                dis: Math.floor(distances),
              }),
            );
          }
        };
        calculateAndDisplayLineDistances();

        var polyline = new kakao.maps.Polyline({
          path: linePath,
          strokeWeight: 5,
          strokeColor: "#33ff57",
          strokeOpacity: 0.7,
          strokeStyle: "solid",
        });
        window.polyline = polyline;
        polyline.setMap(window.map);
        setFindRouteOpen(true);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <SafeDirectionButton onClick={getSafeLocation}>
      안전한 길 찾기
    </SafeDirectionButton>
  );
};

export default SafeDirection;
