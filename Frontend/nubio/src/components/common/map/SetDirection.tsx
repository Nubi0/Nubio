import { SetDirectionWrapper } from "../../../styles/SKakaoMap";
import axios from "axios";
import { SetDirectionProps } from "./../../../types/kakaoMaps.d";
import { useEffect } from "react";

const SetDirection = ({ map }: SetDirectionProps) => {
  const apiKey = "5b3ce3597851110001cf6248b9e727b35dd741be8d930a1f0a2c3b2e";
  const startName = localStorage.getItem("startName")!;
  const endtName = localStorage.getItem("endtName")!;
  const startPoint = localStorage.getItem("startPoint");
  const endPoint = localStorage.getItem("endPoint");
  const getDirection = () => {
    axios
      .get(
        `https://api.openrouteservice.org/v2/directions/wheelchair?api_key=${apiKey}&start=${startPoint}&end=${endPoint}`
      )
      .then((res) => {
        localStorage.setItem(
          "coordinate",
          res.data.features[0].geometry.coordinates
        );
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  // 선을 구성하는 좌표 배열입니다. 이 좌표들을 이어서 선을 표시합니다
  const coordinateList = localStorage.getItem("coordinate") || "";
  var coordinates = coordinateList.split(",");
  console.log(coordinates);
  var linePath = [];
  for (let i = 0; i < coordinates.length; i += 2) {
    const latitude = parseFloat(coordinates[i + 1]);
    const longitude = parseFloat(coordinates[i]);
    const latLng = new kakao.maps.LatLng(latitude, longitude);
    linePath.push(latLng);
  }

  // 지도에 표시할 선을 생성합니다
  var polyline = new kakao.maps.Polyline({
    path: linePath, // 선을 구성하는 좌표배열 입니다
    strokeWeight: 5, // 선의 두께 입니다
    strokeColor: "#FFAE00", // 선의 색깔입니다
    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
    strokeStyle: "solid", // 선의 스타일입니다
  });

  // 지도에 선을 표시합니다
  polyline.setMap(map);
  useEffect(() => {
    getDirection();
  }, []);

  return (
    <SetDirectionWrapper>
      <input type="text" placeholder="출발지" value={startName} />
      <input type="text" placeholder="도착지" value={endtName} />
      <button onClick={getDirection}>길 찾기</button>
    </SetDirectionWrapper>
  );
};

export default SetDirection;
