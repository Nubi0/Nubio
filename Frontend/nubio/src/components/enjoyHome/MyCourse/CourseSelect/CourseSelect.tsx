import { useEffect, useState } from 'react';
import { CourseSelectWrapper } from "../../../../styles/SCourseSelectPage";


declare global {
  interface Window {
    kakao: any;
  }
}


const CourseSelect = () => {
  const [manager, setManager] = useState<any>(null);
  const [overlays, setOverlays] = useState<any[]>([]); // overlays 배열 초기화
  const selectOverlay = (type: any) => {
    // 그리기 중이면 그리기를 취소합니다
    manager.cancel();

    // 클릭한 그리기 요소 타입을 선택합니다
    manager.select(window.kakao.maps.drawing.OverlayType[type]);
  };

  const pointsToPath = (points: any) => {
    var len = points.length,
      path = [],
      i = 0;

    for (; i < len; i++) {
      var latlng = new window.kakao.maps.LatLng(points[i].y, points[i].x);
      path.push(latlng);
    }

    return path;
  };

  const getDataFromDrawingMap = () => {
    var data = manager.getData();
    console.log(data);

    removeOverlays();

    drawPolyline(data[window.kakao.maps.drawing.OverlayType.POLYLINE]);
  };

  const getDrawnLines = () => {
    const drawnData = manager.getData();
    const drawnPolylines = drawnData[window.kakao.maps.drawing.OverlayType.POLYLINE];
    return drawnPolylines;
  }

  // 거리계산 공식
  const calculateLineDistance = (line: any) => {
    const path = line['points'];
    const R = 6371;
    console.log(path);
    let totalDistance = 0;
    for(let i = 0; i < path.length - 1; i++){
      const point1 = path[i];
      const point2 = path[i + 1];
      const dLat = deg2rad(point1['y'] - point2['y']);
      const dLon = deg2rad(point1['x'] - point2['x']);
      const a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(deg2rad(point1['y'])) * Math.cos(deg2rad(point2['y']))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      const distance = R * c * 1000;
      totalDistance += distance;
    }
    return totalDistance;
  }

  const deg2rad = (deg: any) => {
    return deg * (Math.PI/180);
  }

  const calculateAndDisplayLineDistances = () => {
    const drawnLines = getDrawnLines();
    console.log(drawnLines);
    if (drawnLines.length > 0) {
      const distances = drawnLines.map((line: any) => {
        const distance = calculateLineDistance(line);
        return distance.toFixed();
      });
      console.log(distances);
      const walkkTime = (distances / 67) | 0;
      const bicycleTime = (distances / 227) | 0;
      if(walkkTime > 60) {
        console.log(`도보 : ${walkkTime / 60}시간`);
      } else {
        console.log(`도보 : ${walkkTime % 60}분`);
      }

      if(bicycleTime > 60) {
        console.log(`자전거 : ${bicycleTime / 60}시간`);
      } else {
        console.log(`자전거 : ${bicycleTime % 60}분`);
      }
      

    } else {
      console.log('라인이 그려지지 않았습니다.');
    }
  };


  const handleCalculateDistance = () => {
    calculateAndDisplayLineDistances();
  };

  const drawPolyline = (lines: any[]) => { // lines 인자로 받도록 수정
    let len = lines.length, i = 0;

    for (; i < len; i++) {
      const path = pointsToPath(lines[i].points);
      const style = lines[i].options;
      const polyline = new window.kakao.maps.Polyline({
        map: map,
        path: path,
        strokeColor: style.strokeColor,
        strokeOpacity: style.strokeOpacity,
        strokeStyle: style.strokeStyle,
        strokeWeight: style.strokeWeight,
      });

      // overlays 배열에 추가
      setOverlays((prevOverlays) => [...prevOverlays, polyline]);
    }
  };

  const removeOverlays = () => {
    // overlays 배열 내의 모든 도형 삭제
    overlays.forEach((overlay) => overlay.setMap(null));
    setOverlays([]); // overlays 배열 초기화
  };

  useEffect(() => {
    if (window.kakao && window.kakao.maps && window.kakao.maps.drawing) {
      // Drawing Library가 로드되었으므로 Drawing Manager를 초기화하고 사용할 수 있습니다.
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
      <div id="drawingMap" style={{ width: '100%', height: '350px' }} />
      <p>
        <button onClick={() => selectOverlay('POLYLINE')}>선</button>
        <button onClick={getDataFromDrawingMap}>도형 가져오기</button>
        <button onClick={handleCalculateDistance}>거리계산</button>
      </p>
    </CourseSelectWrapper>
  );
};

export default CourseSelect;