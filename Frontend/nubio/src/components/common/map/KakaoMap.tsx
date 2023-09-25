import { useEffect, useState } from "react";
import {
  DestinationWrapper,
  MapWrapper,
  SearchListWrapper,
  SearchResultsWrapper,
} from "../../../styles/SKakaoMap";
import Swal from "sweetalert2";
import { useDispatch, useSelector } from "react-redux";
import { setTime } from "../../../redux/slice/EnjoySlice";
import axios from "axios";
import proj4 from "proj4";
import SearchBar from "../search/SearchBar";
import { MyLocation } from "../../../styles/SSafeHomePage";
import RouteInfo from "../../safeHome/route/RouteInfo";
import {
  setLatitude,
  setLongitude,
  setSafeTime,
  setShortTime,
} from "../../../redux/slice/MapSlice";
import NearbyShelter from "../../safeHome/route/safe/NearbyShelter";

interface placeType {
  place_name: string;
  road_address_name: string;
  address_name: string;
  phone: string;
  place_url: string;
  length: number;
  x: string;
  y: string;
}

const { kakao } = window as any;

declare global {
  interface Window {
    kakaoManager: any;
    map: any;
    infowindow: any;
    ps: any;
    polyline: any;
    startCustomOverlay: any;
    endCustomOverlay: any;
  }
}

const KakaoMap = (props: propsType) => {
  const [startX, setStartX] = useState("");
  const [startY, setStartY] = useState("");
  const [endX, setEndX] = useState("");
  const [endY, setEndY] = useState("");
  const [listIsOpen, setListIsOpen] = useState(false);
  const [findRouteOpen, setFindRouteOpen] = useState(false);

  // 마커를 담는 배열
  let markers: any[] = [];
  let drawnData: any[] = [];
  const dispatch = useDispatch();
  const [startName, setStartName] = useState<any>("");
  const [endName, setEndName] = useState<any>("");
  // 최단거리 길찾기
  const getShortDirection = () => {
    var headers = { appKey: "prZbuvPsM53ADwzJMIxl13StkVuNvAG86O6n4YhF" };
    var data = {
      startX,
      startY,
      endX,
      endY,
      reqCoordType: "WGS84GEO",
      resCoordType: "EPSG3857",
      startName,
      endName,
    };
    axios
      .post(
        "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
        data,
        { headers: headers }
      )
      .then((res) => {
        console.log(res);
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
          flattenArray(feature.geometry.coordinates)
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
            console.log(distances);
            console.log(walkTime);
            dispatch(
              setShortTime({
                time: walkTime,
                type: "분",
                dis: Math.floor(distances),
              })
            );
          }
        };
        calculateAndDisplayLineDistances();

        var polyline = new kakao.maps.Polyline({
          path: linePath,
          strokeWeight: 5,
          strokeColor: "red",
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

  // 안전경로 길찾기
  const getSafeLocation = () => {
    axios
      .post("https:/nubi0.com/safe/v1/safe/recommend/node", {
        start_location: {
          longitude: startX,
          latitude: startY,
        },
        end_location: {
          longitude: endX,
          latitude: endY,
        },
      })
      .then((res) => {
        console.log(res);
        var safeLatitude = res.data.data.content[0].location.latitude;
        var safeLongitude = res.data.data.content[0].location.longitude;
        getSafeDirection(safeLatitude, safeLongitude);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const getSafeDirection = (safeLatitude: any, safeLongitude: any) => {
    var headers = { appKey: "prZbuvPsM53ADwzJMIxl13StkVuNvAG86O6n4YhF" };
    var safeLatituded = safeLatitude?.toString();
    var safeLongituded = safeLongitude?.toString();
    console.log(safeLatitude);
    console.log(safeLongituded);
    var data = {
      startX,
      startY,
      endX,
      endY,
      reqCoordType: "WGS84GEO",
      resCoordType: "EPSG3857",
      startName,
      endName,
      passList: safeLongituded + "," + safeLatituded,
      searchOption: 0,
    };
    console.log(data);
    axios
      .post(
        "https://apis.openapi.sk.com/tmap/routes/pedestrian?version=1&format=json&callback=result",
        data,
        { headers: headers }
      )
      .then((res) => {
        console.log(res);
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
          flattenArray(feature.geometry.coordinates)
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
            console.log(distances);
            console.log(walkTime);
            dispatch(
              setSafeTime({
                time: walkTime,
                type: "분",
                dis: Math.floor(distances),
              })
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
  const getDrawnLines = () => {
    const drawnPolylines =
      drawnData[window.kakao.maps.drawing.OverlayType.POLYLINE];
    return drawnPolylines;
  };

  // 거리가 계산된 결과 출력 함수
  const calculateAndDisplayLineDistances = () => {
    const drawnLines = getDrawnLines();

    if (drawnLines.length > 0) {
      const distances = drawnLines.map((line: any) => {
        const distance = calculateLineDistance(line);
        return distance.toFixed();
      });
      const walkTime = (distances / 67) | 0;
      if (walkTime > 60) {
        dispatch(
          setTime({
            time: Math.ceil(walkTime / 60),
            type: "시간",
            dis: distances[0],
          })
        );
      } else {
        dispatch(
          setTime({ time: walkTime % 60, type: "분", dis: distances[0] })
        );
      }
    } else {
      console.log("라인이 그려지지 않았습니다.");
    }
  };

  // 거리계산 공식
  const calculateLineDistance = (line: any) => {
    console.log(line);
    const path = line["points"];
    const R = 6371;
    let totalDistance = 0;
    for (let i = 0; i < path.length - 1; i++) {
      const point1 = path[i];
      const point2 = path[i + 1];
      const dLat = deg2rad(point1["y"] - point2["y"]);
      const dLon = deg2rad(point1["x"] - point2["x"]);
      console.log(dLat);
      console.log(dLon);
      const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(point1["y"])) *
          Math.cos(deg2rad(point2["y"])) *
          Math.sin(dLon / 2) *
          Math.sin(dLon / 2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      const distance = R * c * 1000;
      totalDistance += distance;
    }
    return totalDistance;
  };

  // 도(degree)단위를 라디안(radian)단위로 바꾸는 함수
  const deg2rad = (deg: any) => {
    return deg * (Math.PI / 180);
  };

  // 키워드 검색을 요청하는 함수
  function searchPlaces(keyword: string) {
    if (!keyword.replace(/^\s+|\s+$/g, "")) {
      console.log("키워드를 입력해주세요!");
      return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청
    window.ps.keywordSearch(keyword, placesSearchCB);
  }

  // 검색 결과가 없을 때는 search-result 요소를 숨기는 함수
  function hideSearchResult() {
    const searchResult = document.getElementById("search-result");
    if (searchResult) {
      searchResult.style.display = "none";
    }
  }

  // 장소검색이 완료됐을 때 호출되는 콜백함수
  function placesSearchCB(data: any, status: any, pagination: any) {
    if (status === kakao.maps.services.Status.OK) {
      // 정상적으로 검색이 완료됐으면
      // 검색 목록과 마커를 표출
      displayPlaces(data);
      // 페이지 번호를 표출
      displayPagination(pagination);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      Swal.fire({
        title: "검색 결과가 존재하지 않습니다.",
        text: "Nubio",
      });
      hideSearchResult();
      return;
    } else if (status === kakao.maps.services.Status.ERROR) {
      Swal.fire({
        title: "검색 결과 중 오류가 발생했습니다.",
        text: "Nubio",
      });

      return;
    }
  }

  // 검색 결과 목록과 마커를 표출하는 함수
  function displayPlaces(places: string | any[]) {
    const listEl = document.getElementById("places-list"),
      resultEl = document.getElementById("search-result"),
      fragment = document.createDocumentFragment(),
      bounds = new kakao.maps.LatLngBounds();

    // 검색 결과 목록에 추가된 항목들을 제거
    listEl && removeAllChildNods(listEl);

    // 지도에 표시되고 있는 마커를 제거
    removeMarker();
    for (var i = 0; i < places.length; i++) {
      // 마커를 생성하고 지도에 표시
      let placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
        marker = addMarker(placePosition, i, undefined),
        itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가
      bounds.extend(placePosition);

      // 마커와 검색결과 항목에 mouseover 했을때
      // 해당 장소에 인포윈도우에 장소명을 표시
      // mouseout 했을 때는 인포윈도우를 닫기
      (function (marker, title) {
        kakao.maps.event.addListener(marker, "mouseover", function () {
          displayInfowindow(marker, title);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
          window.infowindow.close();
        });

        itemEl.onmouseover = function () {
          displayInfowindow(marker, title);
        };

        itemEl.onmouseout = function () {
          window.infowindow.close();
        };

        // 스마트폰 터치 및 클릭 이벤트 처리
        itemEl.addEventListener("click", function () {
          displayInfowindow(marker, title);
        });

        itemEl.addEventListener("touchstart", function () {
          displayInfowindow(marker, title);
        });

        itemEl.addEventListener("touchend", function () {
          window.infowindow.close();
        });
      })(marker, places[i].place_name);

      fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가
    listEl && listEl.appendChild(fragment);
    if (resultEl) {
      resultEl.scrollTop = 0;
    }

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정
    window.map.setBounds(bounds);
  }

  // 검색결과 항목을 Element로 반환하는 함수
  function getListItem(index: number, places: placeType) {
    const el = document.createElement("li");
    const onClickStart = () => {
      setStartX(places.x);
      setStartY(places.y);
      setStartName(places.place_name);
      removeMarker();
      window.polyline?.setMap(null);
      window.startCustomOverlay?.setMap(null);

      let content = `<div class ="label"  style="background:#FFC542; font-size:0.8rem; border:0.5px solid black; padding:0.3rem; border-radius:1rem;"></span><span class="center">${places.place_name}</span><span class="right"></span></div>`;
      // 커스텀 오버레이가 표시될 위치입니다
      let markerPosition = new kakao.maps.LatLng(places.y, places.x);
      // 커스텀 오버레이를 생성합니다
      let customOverlay = new kakao.maps.CustomOverlay({
        position: markerPosition,
        content: content,
      });
      window.startCustomOverlay = customOverlay;
      // 커스텀 오버레이를 지도에 표시합니다
      window.startCustomOverlay.setMap(window.map);
    };
    const onClickEnd = () => {
      setEndX(places.x);
      setEndY(places.y);
      setEndName(places.place_name);
      removeMarker();
      window.polyline?.setMap(null);
      window.endCustomOverlay?.setMap(null);

      let content = `<div class ="label" style="background:#F25260; font-size:0.8rem; border:0.5px solid black; padding:0.3rem; border-radius:1rem;"><span class="left"></span><span class="center">${places.place_name}</span><span class="right"></span></div>`;
      let markerPosition = new kakao.maps.LatLng(places.y, places.x);
      let customOverlay = new kakao.maps.CustomOverlay({
        position: markerPosition,
        content: content,
      });

      window.endCustomOverlay = customOverlay;
      window.endCustomOverlay.setMap(window.map);
    };
    if (places.length !== 0) {
      let itemStr = `
        <div class="info">
        <div class="name">
            <h3 class="info-item place-name">${index + 1}. ${
        places.place_name
      }<a id="homePage" href=${places.place_url}>상세보기</a>
      </h3>
        
    </div>
            ${
              places.road_address_name
                ? `<span class="address ">
                  ${places.road_address_name}
                    ${places.address_name}
                    </span>`
                : `<span class="address ">
                    ${places.address_name}
                </span>`
            }
            <span class="tel">
              ${places.phone}
            </span>
            <div class="bnt">
              <button id="start"> 출발</button>
              <button id="end">도착</button>
            </div>
          </a>
        </div>
        `;
      el.innerHTML = itemStr;
      el.className = "item";
      const startButton = el.querySelector("#start");
      startButton?.addEventListener("click", onClickStart);
      const endButton = el.querySelector("#end");
      endButton?.addEventListener("click", onClickEnd);
    }

    return el;
  }
  // 마커를 생성하고 지도 위에 마커를 표시하는 함수
  function addMarker(position: any, idx: number, title: undefined) {
    var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지
      imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

    marker.setMap(window.map); // 지도 위에 마커를 표출
    markers.push(marker); // 배열에 생성된 마커를 추가

    return marker;
  }

  // 지도 위에 표시되고 있는 마커를 모두 제거합니다
  function removeMarker() {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(null);
    }
    markers = [];
  }

  // 검색결과 목록 하단에 페이지번호를 표시는 함수
  function displayPagination(pagination: {
    last: number;
    current: number;
    gotoPage: (arg0: number) => void;
  }) {
    const paginationEl = document.getElementById("pagination") as HTMLElement;
    let fragment = document.createDocumentFragment();
    let i;

    // 기존에 추가된 페이지번호를 삭제
    while (paginationEl.hasChildNodes()) {
      paginationEl.lastChild &&
        paginationEl.removeChild(paginationEl.lastChild);
    }

    for (i = 1; i <= pagination.last; i++) {
      const el = document.createElement("a") as HTMLAnchorElement;
      el.href = "#";
      el.innerHTML = i.toString();

      if (i === pagination.current) {
        el.className = "on";
      } else {
        el.onclick = (function (i) {
          return function () {
            pagination.gotoPage(i);
          };
        })(i);
      }

      fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
  }

  // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수
  // 인포윈도우에 장소명을 표시
  function displayInfowindow(marker: any, title: string) {
    const content =
      '<div style="padding:5px;z-index:1;" class="marker-title">' +
      title +
      "</div>";

    window.infowindow.setContent(content);
    window.infowindow.open(window.map, marker);
  }

  // 검색결과 목록의 자식 Element를 제거하는 함수
  function removeAllChildNods(el: HTMLElement) {
    while (el.hasChildNodes()) {
      el.lastChild && el.removeChild(el.lastChild);
    }
  }

  // 검색어가 바뀔 때마다 재렌더링되도록 useEffect 사용
  useEffect(() => {
    // 현재위치
    const startCurPosition = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          (position) => {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            dispatch(setLatitude(latitude));
            dispatch(setLongitude(longitude));
            const map = window.map;
            map.setCenter(new window.kakao.maps.LatLng(latitude, longitude));
            // 현재 위치에 마커를 표시
            const marker = new kakao.maps.Marker({
              position: new kakao.maps.LatLng(latitude, longitude),
            });
            marker.setMap(map); // 마커를 지도에 표시
            console.log(latitude);
            console.log(longitude);
          },
          (error) => {
            console.error("geolocation 에러 발생:", error);
          }
        );
      } else {
        console.error("지금 브라우저에서는 geolocation를 지원하지 않습니다.");
      }
    };
    startCurPosition();
    const mapContainer = document.getElementById("map");
    const mapOption = {
      // center: new kakao.maps.LatLng(window.mylatitude, window.mylongitude),
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 3, // 지도의 확대 레벨
    };

    // 지도를 생성
    const map = new kakao.maps.Map(mapContainer, mapOption);
    window.map = map;
    // Drawing Manger Option 설정
    const options = {
      map: map,
      drawingMode: [window.kakao.maps.drawing.OverlayType.POLYLINE],
      guideTooltip: ["draw", "drag"],
      markerOptions: {
        draggable: true,
        removable: true,
      },
      polylineOptions: {
        draggable: true,
        editable: true,
        strokeColor: "#FFC542",
        hintStrokeStyle: "solid",
        hintStrokeOpacity: 1,
        zIndex: 1000,
      },
    };

    // Drawing Manager 객체 생성
    const managerInstance = new window.kakao.maps.drawing.DrawingManager(
      options
    );
    managerInstance.addListener("drawend", () => {
      drawnData = managerInstance.getData();
      calculateAndDisplayLineDistances();
    });
    window.kakaoManager = managerInstance;

    // 커스텀 마커 표시
    for (var i = 0; i < props.position.length; i++) {
      const customOverlay = new window.kakao.maps.CustomOverlay({
        content: `
          <div>
            <img class="custom-marker" src="${props.position[i].img_url}" alt="Custom Marker" />
          </div>
        `,
        position: new window.kakao.maps.LatLng(
          props.position[i].lat,
          props.position[i].lng
        ),
      });

      customOverlay.setMap(map);
    }

    // 장소 검색 객체를 생성
    const ps = new kakao.maps.services.Places();
    window.ps = ps;
    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
    window.infowindow = infowindow;

    // 맵 클릭 시 검색리스트 안보임
    window.map.addListener("click", () => {
      setListIsOpen(false);
      // setFindRouteOpen(false);
    });
  }, []);
  return (
    <>
      <MapWrapper id="map" className="map" />
      <NearbyShelter></NearbyShelter>
      <SearchBar
        searchPlaces={searchPlaces}
        setListIsOpen={setListIsOpen}
        setFindRouteOpen={setFindRouteOpen}
      />
      {/* <MyLocation onClick={startCurPosition}>내 위치</MyLocation> */}
      {findRouteOpen ? <RouteInfo /> : null}
      {props.searchKeyword !== "" && listIsOpen ? (
        <SearchResultsWrapper id="search-result">
          <p className="result-text">
            {props.searchKeyword}
            검색 결과
          </p>
          <DestinationWrapper>
            <h4>출발지 : {startName}</h4>
            <h4>도착지 : {endName}</h4>
          </DestinationWrapper>
          <button id="short" onClick={getShortDirection}>
            빠른 길 찾기
          </button>
          <button id="safe" onClick={getSafeLocation}>
            안전한 길 찾기
          </button>
          <SearchListWrapper className="scroll-wrapper">
            <ul id="places-list"></ul>
          </SearchListWrapper>
          <div id="pagination"></div>
        </SearchResultsWrapper>
      ) : null}
    </>
  );
};

export default KakaoMap;
