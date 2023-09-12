import { useEffect, useState } from 'react';
import {
  CourseSelectWrapper,
  CourseMaker,
  MapWrapper,
} from '../../../../styles/SCourseSelectPage';

declare global {
  interface Window {
    kakao: any;
  }
}

const CourseSelect = () => {
  const [manager, setManager] = useState<any>(null);

  manager.addListener('drawend', () => {
    calculateAndDisplayLineDistances();
  });

  const selectOverlay = (type: any) => {
    // 그리기 중이면 그리기를 취소합니다
    manager.cancel();

    manager.undo();

    // 클릭한 그리기 요소 타입을 선택합니다
    manager.select(window.kakao.maps.drawing.OverlayType[type]);
  };

  // 그려진 선의 데이터를 받아오는 함수
  const getDrawnLines = () => {
    const drawnData = manager.getData();
    const drawnPolylines = drawnData[window.kakao.maps.drawing.OverlayType.POLYLINE];
    return drawnPolylines;
  };

  // 거리계산 공식
  const calculateLineDistance = (line: any) => {
    const path = line['points'];
    const R = 6371;
    console.log(path);
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
    console.log(drawnLines);
    if (drawnLines.length > 0) {
      const distances = drawnLines.map((line: any) => {
        const distance = calculateLineDistance(line);
        return distance.toFixed();
      });
      console.log(distances[0]);
      const walkkTime = (distances / 67) | 0;
      const bicycleTime = (distances / 227) | 0;
      if (walkkTime > 60) {
        console.log(`도보 : ${walkkTime / 60}시간`);
      } else {
        console.log(`도보 : ${walkkTime % 60}분`);
      }

      if (bicycleTime > 60) {
        console.log(`자전거 : ${bicycleTime / 60}시간`);
      } else {
        console.log(`자전거 : ${bicycleTime % 60}분`);
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

      const options = {
        map: drawingMap,
        drawingMode: [window.kakao.maps.drawing.OverlayType.POLYLINE],
        guideTooltip: ['draw', 'drag', 'edit'],
        markerOptions: {
          draggable: true,
          removable: true,
        },
        polylineOptions: {
          draggable: true,
          removable: true,
          editable: true,
          strokeColor: '#39f',
          hintStrokeStyle: 'dash',
          hintStrokeOpacity: 0.5,
        },
      };
      // Drawing Manager 초기화 및 사용 코드 추가
      const managerInstance = new window.kakao.maps.drawing.DrawingManager(options);
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
    </CourseSelectWrapper>
  );
};

export default CourseSelect;
