import { useState, useRef } from 'react';
import { CourseSelectWrapper, MapWrapper } from "../../../../styles/SCourseSelectPage";
import { Map, DrawingManager } from 'react-kakao-maps-sdk';

const CourseSelect =() => {
    type DrawingManagerType = kakao.maps.drawing.DrawingManager<kakao.maps.drawing.OverlayType.POLYLINE>
    const managerRef = useRef<DrawingManagerType>(null)

    const [overlayData, setOverlayData] = useState<
    ReturnType<DrawingManagerType["getData"]>
  >({
    arrow: [],
    circle: [],
    ellipse: [],
    marker: [],
    polygon: [],
    polyline: [],
    rectangle: [],
  })

  function selectOverlay(
    type: string)  {
    const manager = managerRef.current
    if(manager){
        manager.cancel()
        manager.select(type)
    }
  }

  function drawOverlayData() {
    const manager = managerRef.current
    if(manager){
        setOverlayData(manager.getData())
    }
  }

  // Drawing Manager에서 가져온 데이터 중
  // 선과 다각형의 꼭지점 정보를 latlng 배열로 반환하는 함수입니다
  function pointsToPath(points: Array<{ x: number; y: number }>) {
    return points.map((point) => ({
      lat: point.y,
      lng: point.x,
    }))
  }
    return(
        <CourseSelectWrapper>
            <Map
        center={{
          // 지도의 중심좌표
          lat: 33.450701,
          lng: 126.570667,
        }}
        style={{
          width: "100%",
          height: "450px",
        }}
        level={3} // 지도의 확대 레벨
      >
        <DrawingManager
          ref={managerRef}
          drawingMode={['polyline']}
          guideTooltip={["draw", "drag", "edit"]}
          polylineOptions={{
            // 선 옵션입니다
            draggable: true, // 그린 후 드래그가 가능하도록 설정합니다
            removable: true, // 그린 후 삭제 할 수 있도록 x 버튼이 표시됩니다
            editable: true, // 그린 후 수정할 수 있도록 설정합니다
            strokeColor: "#39f", // 선 색
            hintStrokeStyle: "dash", // 그리중 마우스를 따라다니는 보조선의 선 스타일
            hintStrokeOpacity: 0.5, // 그리중 마우스를 따라다니는 보조선의 투명도
          }}
        />
      </Map>
      <div
        style={{
          display: "flex",
          gap: "8px",
        }}
      >
        <button
          onClick={(e) => {
            selectOverlay('polyline')
          }}
        >
          선
        </button>
        </div>
        </CourseSelectWrapper>
    );
};

export default CourseSelect;