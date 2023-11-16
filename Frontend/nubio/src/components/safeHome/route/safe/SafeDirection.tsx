// Hook
import { useDispatch, useSelector } from "react-redux";
// 라이브러리
import axios from "axios";
import Swal from "sweetalert2";
// 컴포넌트
import NearbySafetyFacilities from "./NearbySafetyFacilities";
// 스타일
import { SafeDirectionButton } from "@styles/SRoute";
// redux
import {
  setSafeTime,
  setSafePlace,
  setSafeMarkerList,
} from "@redux/slice/SafeSlice";
import { useState } from "react";

const SafeDirection = ({
  clearRoute,
  setFindRouteOpen,
}: SafeDirectionProps) => {
  const dispatch = useDispatch();

  const start = useSelector(
    (state: { map: { start: StartCoordinates } }) => state.map.start,
  );
  const end = useSelector(
    (state: { map: { end: EndCoordinates } }) => state.map.end,
  );
  const policeIcon = process.env.PUBLIC_URL + "/assets/markerIcon/police.png";
  const convenienceIcon =
    process.env.PUBLIC_URL + "/assets/markerIcon/convenience.png";
  const [safeLocationCompleted, setSafeLocationCompleted] = useState(false);

  const getSafeDirections = () => {
    clearRoute();
    axios
      .get(
        `${process.env.REACT_APP_SERVER_URL}/path/route/v1/foot/${start.x},${start.y};${end.x},${end.y}?steps=true`,
      )
      .then((res) => {
        function flattenArray(arr: any) {
          return arr.reduce((acc: any, val: any) => {
            if (val.location.length > 0) {
              acc.push(val.location[0], val.location[1]);
            }
            return acc;
          }, []);
        }
        const coordinates = res.data.routes[0].legs[0].steps.flatMap(
          (feature: any) => flattenArray(feature.intersections),
        );
        const coordinatesList = [];
        for (let i = 0; i < coordinates.length; i += 2) {
          if (i + 1 < coordinates.length) {
            coordinatesList.push([coordinates[i], coordinates[i + 1]]);
          }
        }
        const linePath: any = [];
        for (let i = 0; i < coordinatesList?.length; i++) {
          const latitude = parseFloat(coordinatesList[i][0]);
          const longitude = parseFloat(coordinatesList[i][1]);
          const latLng = new window.kakao.maps.LatLng(longitude, latitude);
          linePath.push(latLng);
        }
        dispatch(
          setSafeTime({
            time: Math.floor(res.data.routes[0].duration / 60),
            type: "분",
            dis: Math.floor(res.data.routes[0].distance),
          }),
        );
        var polyline = new window.kakao.maps.Polyline({
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
      .catch(() => {
        Swal.fire({
          title: "조회할 수 없는 장소입니다.",
          text: "NUBIO",
        });
      });
  };
  // 안전경로 길찾기
  const getSafeLocation = () => {
    axios
      .post(`${process.env.REACT_APP_SERVER_URL}/safe/v1/safe/recommend/node`, {
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
          var safePlaces = res.data.data.content[0].safety_facilities;
          dispatch(setSafePlace(safePlaces));
          if (safePlaces.length > 1) {
            console.log(safePlaces);
            for (let i = 0; i < safePlaces.length; i++) {
              let placeName;
              let placeIcon;
              if (safePlaces[i].facility_type == "CONVENIENCE_STORE") {
                placeName = "편의점";
                placeIcon = convenienceIcon;
              }
              if (safePlaces[i].facility_type == "POLICE") {
                placeName = "경찰서";
                placeIcon = policeIcon;
              }
              if (safePlaces[i].facility_type == "LAMP") {
                placeName = "가로등";
              }
              if (safePlaces[i].facility_type == "SAFETY_BELL") {
                placeName = "안전벨";
              }
              //   let content = `<div class ="label"  style="background:#33ff57; font-size:0.8rem; border:0.5px solid white; padding:0.3rem; border-radius:1rem; color:white;"></span><span class="center">
              // ${placeName}</span><span class="right"></span></div>`;
              let markerPosition = new window.kakao.maps.LatLng(
                safePlaces[i].location.latitude,
                safePlaces[i].location.longitude,
              );
              const imageSize = new window.kakao.maps.Size(34, 39);
              // const imageOption = {
              //   offset: new window.kakao.maps.Point(27, 69),
              // };
              var markerImage = new window.kakao.maps.MarkerImage(
                placeIcon,
                imageSize,
                // imageOption,
              );
              let maker = new window.kakao.maps.Marker({
                position: markerPosition,
                image: markerImage,
                // content: content,
              });
              window.safeCustomOverlay = maker;
              window.safeCustomOverlay.setMap(window.map);
              dispatch(setSafeMarkerList(maker));
            }
          }
        }
        getSafeDirections();
        setSafeLocationCompleted(true);
      })
      .catch(() => {});
  };

  return (
    <SafeDirectionButton onClick={getSafeLocation}>
      안전한 길 찾기
      {safeLocationCompleted && <NearbySafetyFacilities />}
    </SafeDirectionButton>
  );
};

export default SafeDirection;
