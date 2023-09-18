import { useState } from "react";
import {
  DirectionButtonWrapper,
  ItemInfoWrapper,
  ItemNameWrapper,
  SearchItemWrapper,
} from "../../../styles/SKakaoMap";
import axios from "axios";

import { placeType } from "../../../types/kakaoMaps";

const SearchItem = ({ place, index }: { place: placeType; index: number }) => {
  const apiKey = "5b3ce3597851110001cf6248b9e727b35dd741be8d930a1f0a2c3b2e";

  const onClickStart = () => {
    localStorage.setItem("startPoint", `${place.x},${place.y}`);
    localStorage.setItem("startName", `${place.place_name}`);
  };
  const onClickEnd = () => {
    localStorage.setItem("endPoint", `${place.x},${place.y}`);
    localStorage.setItem("endtName", `${place.place_name}`);
  };
  const startPoint = localStorage.getItem("startPoint");
  const endPoint = localStorage.getItem("endPoint");
  const getDirection = () => {
    axios
      .get(
        `https://api.openrouteservice.org/v2/directions/driving-car?api_key=${apiKey}&start=${startPoint}&end=${endPoint}`
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
  const coordinateList = localStorage.getItem("coordinate");
  var linePath = [coordinateList];
  console.log(linePath);
  // 지도에 표시할 선을 생성합니다
  // var polyline = new kakao.maps.Polyline({
  //   path: linePath, // 선을 구성하는 좌표배열 입니다
  //   strokeWeight: 5, // 선의 두께 입니다
  //   strokeColor: "#FFAE00", // 선의 색깔입니다
  //   strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
  //   strokeStyle: "solid", // 선의 스타일입니다
  // });

  // 지도에 선을 표시합니다
  // polyline.setMap(map);
  return (
    <SearchItemWrapper>
      <ItemNameWrapper>
        <h5>
          {index + 1}. {place.place_name}
        </h5>
        <a id="homePage" href={place.place_url}>
          상세보기
        </a>
      </ItemNameWrapper>
      <ItemInfoWrapper>
        {place.road_address_name ? (
          <p className="address">{place.road_address_name}</p>
        ) : (
          <p className="address">{place.address_name}</p>
        )}
        <p className="tel">{place.phone}</p>
      </ItemInfoWrapper>
      <DirectionButtonWrapper>
        <button onClick={onClickStart}>출발</button>
        <button onClick={onClickEnd}>도착</button>
        <button onClick={getDirection}>고</button>
      </DirectionButtonWrapper>
    </SearchItemWrapper>
  );
};

export default SearchItem;
