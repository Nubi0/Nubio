import React, { useEffect, useRef } from 'react';
import {
  MapWrapper,
  SearchListWrapper,
  SearchResultWrapper,
} from '../../../styles/SKakaoMap';
import Swal from 'sweetalert2';
import { useDispatch } from 'react-redux';
import { setTime } from '../../../redux/slice/EnjoySlice';

interface placeType {
  place_name: string;
  road_address_name: string;
  address_name: string;
  phone: string;
  place_url: string;
  length: number;
}

// head에 작성한 Kakao API 불러오기
const { kakao } = window as any;

declare global {
  interface Window {
    kakaoManager: any; // 또는 DrawingManager 타입에 맞는 타입 지정
  }
}

const KakaoMap = (props: propsType) => {
  // 마커를 담는 배열
  let markers: any[] = [];
  let drawnData: any[] = [];
  const dispatch = useDispatch();

  const getDrawnLines = () => {
    const drawnPolylines = drawnData[window.kakao.maps.drawing.OverlayType.POLYLINE];
    return drawnPolylines;
  };

  // 거리가 계산된 결과 출력 함수
  const calculateAndDisplayLineDistances = () => {
    const drawnLines = getDrawnLines();
    console.log(drawnLines);
    if (drawnLines.length > 0) {
      const distances = drawnLines.map((line: any) => {
        const distance = calculateLineDistance(line);
        return distance.toFixed();
      });
      console.log(distances[0]);
      const walkkTime = (distances / 67) | 0;
      if (walkkTime > 60) {
        dispatch(
          setTime({ time: Math.ceil(walkkTime / 60), type: '시간', dis: distances[0] })
        );
      } else {
        dispatch(setTime({ time: walkkTime % 60, type: '분', dis: distances[0] }));
      }
    } else {
      console.log('라인이 그려지지 않았습니다.');
    }
  };

  // 거리계산 공식
  const calculateLineDistance = (line: any) => {
    const path = line['points'];
    const R = 6371;
    let totalDistance = 0;
    for (let i = 0; i < path.length - 1; i++) {
      const point1 = path[i];
      const point2 = path[i + 1];
      const dLat = deg2rad(point1['y'] - point2['y']);
      const dLon = deg2rad(point1['x'] - point2['x']);
      const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(point1['y'])) *
          Math.cos(deg2rad(point2['y'])) *
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

  // 검색어가 바뀔 때마다 재렌더링되도록 useEffect 사용
  useEffect(() => {
    const mapContainer = document.getElementById('map');
    const mapOption = {
      center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
      level: 3, // 지도의 확대 레벨
    };

    // 지도를 생성
    const map = new kakao.maps.Map(mapContainer, mapOption);

    // Drawing Manger Option 설정
    const options = {
      map: map,
      drawingMode: [window.kakao.maps.drawing.OverlayType.POLYLINE],
      guideTooltip: ['draw', 'drag'],
      markerOptions: {
        draggable: true,
        removable: true,
      },
      polylineOptions: {
        draggable: true,
        editable: true,
        strokeColor: '#FFC542',
        hintStrokeStyle: 'solid',
        hintStrokeOpacity: 1,
        zIndex: 1000,
      },
    };
    // Drawing Manager 객체 생성
    const managerInstance = new window.kakao.maps.drawing.DrawingManager(options);
    managerInstance.addListener('drawend', () => {
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
    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성
    const infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

    // 키워드로 장소를 검색합니다
    searchPlaces();

    // 키워드 검색을 요청하는 함수
    function searchPlaces() {
      let keyword = props.searchKeyword;

      if (!keyword.replace(/^\s+|\s+$/g, '')) {
        console.log('키워드를 입력해주세요!');
        return false;
      }

      // 장소검색 객체를 통해 키워드로 장소검색을 요청
      ps.keywordSearch(keyword, placesSearchCB);
    }

    // 검색 결과가 없을 때는 search-result 요소를 숨기는 함수
    function hideSearchResult() {
      const searchResult = document.getElementById('search-result');
      if (searchResult) {
        searchResult.style.display = 'none';
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
          title: '검색 결과가 존재하지 않습니다.',
          text: 'Nubio',
        });
        hideSearchResult();
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        Swal.fire({
          title: '검색 결과 중 오류가 발생했습니다.',
          text: 'Nubio',
        });

        return;
      }
    }

    // 검색 결과 목록과 마커를 표출하는 함수
    function displayPlaces(places: string | any[]) {
      console.log(places);
      const listEl = document.getElementById('places-list'),
        resultEl = document.getElementById('search-result'),
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
          kakao.maps.event.addListener(marker, 'mouseover', function () {
            displayInfowindow(marker, title);
          });

          kakao.maps.event.addListener(marker, 'mouseout', function () {
            infowindow.close();
          });

          itemEl.onmouseover = function () {
            displayInfowindow(marker, title);
          };

          itemEl.onmouseout = function () {
            infowindow.close();
          };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Element에 추가
      listEl && listEl.appendChild(fragment);
      if (resultEl) {
        resultEl.scrollTop = 0;
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정
      map.setBounds(bounds);
    }

    // 검색결과 항목을 Element로 반환하는 함수
    function getListItem(index: number, places: placeType) {
      const el = document.createElement('li');
      if (places.length !== 0) {
        let itemStr = `
        <div class="info">
        <div class="name">
            <h5 class="info-item place-name">${index + 1}. ${places.place_name}</h5>
        <a id="homePage" href=${places.place_url}>상세보기</a>
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
          </a>
        </div>
        `;
        el.innerHTML = itemStr;
        el.className = 'item';
      }

      return el;
    }

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수
    function addMarker(position: any, idx: number, title: undefined) {
      var imageSrc =
          'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지
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

      marker.setMap(map); // 지도 위에 마커를 표출
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
      const paginationEl = document.getElementById('pagination') as HTMLElement;
      let fragment = document.createDocumentFragment();
      let i;

      // 기존에 추가된 페이지번호를 삭제
      while (paginationEl.hasChildNodes()) {
        paginationEl.lastChild && paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        const el = document.createElement('a') as HTMLAnchorElement;
        el.href = '#';
        el.innerHTML = i.toString();

        if (i === pagination.current) {
          el.className = 'on';
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
        '<div style="padding:5px;z-index:1;" class="marker-title">' + title + '</div>';

      infowindow.setContent(content);
      infowindow.open(map, marker);
    }

    // 검색결과 목록의 자식 Element를 제거하는 함수
    function removeAllChildNods(el: HTMLElement) {
      while (el.hasChildNodes()) {
        el.lastChild && el.removeChild(el.lastChild);
      }
    }
  }, [props.searchKeyword]);
  return (
    <>
      <MapWrapper id="map" className="map" />
      {props.searchKeyword !== '' ? (
        <SearchResultWrapper id="search-result">
          <p className="result-text">
            {props.searchKeyword}
            검색 결과
          </p>
          <SearchListWrapper className="scroll-wrapper">
            <ul id="places-list"></ul>
          </SearchListWrapper>
          <div id="pagination"></div>
        </SearchResultWrapper>
      ) : null}
    </>
  );
};

export default KakaoMap;
