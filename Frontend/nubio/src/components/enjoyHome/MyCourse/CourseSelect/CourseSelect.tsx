import { useEffect, useState } from 'react';
import {
  CourseSelectWrapper,
  CourseMaker,
  MapWrapper,
  ModalOpen
} from '../../../../styles/SCourseSelectPage';
import CoursePinList from './CourseList';
import CourseResult from './CourseResult';

declare global {
  interface Window {
    kakao: any;
  }
}

const CourseSelect = ({setModal}: any) => {
  const [manager, setManager] = useState<any>(null);
  let drawnData: any = null;
  const [map, setMap] = useState<any>(null);
  const [timeData, setTimeData] = useState<t_d_DataProps | null>(null)
  const dummy1 = process.env.PUBLIC_URL + '/assets/dummy/dummy1.jpg'
  const dummyUrl = process.env.PUBLIC_URL + '/assets/dummy/dummy2.jpg';

  var positions = [
    {
        title: '카카오', 
        latlng: new window.kakao.maps.LatLng(33.450705, 126.570677),
        img_url: dummy1
    },
    {
        title: '생태연못', 
        latlng: new window.kakao.maps.LatLng(33.450936, 126.569477),
        img_url: dummyUrl,
    },
    {
        title: '텃밭', 
        latlng: new window.kakao.maps.LatLng(33.450879, 126.569940),
        img_url: dummyUrl,
    },
    {
        title: '근린공원',
        latlng: new window.kakao.maps.LatLng(33.451393, 126.570738),
        img_url: dummyUrl
    }
];

  // 커스텀 마커 생성
  
  for (var i = 0; i < positions.length; i ++) {
  
    const customOverlay = new window.kakao.maps.CustomOverlay({
      content: `
        <div>
          <img class="custom-marker" src="${positions[i].img_url}" alt="Custom Marker" />
        </div>
      `,
      position: positions[i].latlng,
    });
  
    customOverlay.setMap(map);
  }

  const selectOverlay = (type: any) => {
    setTimeData(null);
    // 그리기 중이면 그리기를 취소합니다
    manager.cancel();

    manager.undo();

    // 클릭한 그리기 요소 타입을 선택합니다
    manager.select(window.kakao.maps.drawing.OverlayType[type]);
  };

  // 그려진 선의 데이터를 받아오는 함수
  const getDrawnLines = () => {
      const drawnPolylines = drawnData[window.kakao.maps.drawing.OverlayType.POLYLINE];
      return drawnPolylines;
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

  // 거리가 계산된 결과 출력 함수
  const calculateAndDisplayLineDistances = () => {
    const drawnLines = getDrawnLines();
    if (drawnLines.length > 0) {
      const distances = drawnLines.map((line: any) => {
        const distance = calculateLineDistance(line);
        return distance.toFixed();
      });
      console.log(distances[0]);
      const walkkTime = (distances / 67) | 0;
      if (walkkTime > 60) {
        setTimeData({time: walkkTime / 60, type: '시간', dis: distances[0]});
      } else {
        setTimeData({time: walkkTime % 60, type: '분', dis: distances[0]});
      }
    } else {
      console.log('라인이 그려지지 않았습니다.');
    }
  };

  useEffect(() => {
    if (window.kakao && window.kakao.maps && window.kakao.maps.drawing) {
      const drawingMapContainer = document.getElementById('drawingMap');
      const drawingoption = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 3,
      };

      const drawingMap = new window.kakao.maps.Map(drawingMapContainer, drawingoption);
      setMap(drawingMap);
      const options = {
        map: drawingMap,
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
      // Drawing Manager 초기화 및 사용 코드 추가
      const managerInstance = new window.kakao.maps.drawing.DrawingManager(options);
      managerInstance.addListener('drawend', () => {
        drawnData = managerInstance.getData();
        calculateAndDisplayLineDistances();
      });
      setManager(managerInstance);
    } else {
      console.error('Kakao Maps Drawing Library is not available.');
    }
  }, []);

  return (
    <CourseSelectWrapper>
      <MapWrapper id="drawingMap" />
      <CourseMaker onClick={() => selectOverlay('POLYLINE')} id="courseMaker">
        코스 그리기
      </CourseMaker>
      <ModalOpen onClick={setModal}>
        모달 열기
      </ModalOpen>
      <CoursePinList positions={positions} />
      {timeData && <CourseResult data={timeData} />}
    </CourseSelectWrapper>
  );
};

export default CourseSelect;
