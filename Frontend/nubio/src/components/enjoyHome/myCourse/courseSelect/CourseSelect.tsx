import { useEffect, useState } from 'react';
import { CourseSelectWrapper, CourseMaker } from '../../../../styles/SCourseSelectPage';
import CourseList from './CourseList';
import { useDispatch, useSelector } from 'react-redux';
import { setTime } from '../../../../redux/slice/EnjoySlice';
import Map from '../../../common/map/Map';
declare global {
  interface Window {
    kakao: any;
  }
}

const CourseSelect = () => {
  const dispatch = useDispatch();
  const [manager, setManger] = useState<any>(null);

  var positions = useSelector((state: {enjoy: {positions: placeItem[]}}) => state.enjoy.positions);

  // 커스텀 마커 생성

  useEffect(() => {
    dispatch(setTime(null));
    setManger(window.kakaoManager);
    positions.map((value: placeItem, idx: number) => {
      const positions = new kakao.maps.LatLng(value.y, value.x);
      const marker = new kakao.maps.Marker({
        map: window.map,
        position: positions,
      })
      console.log(marker.getPosition());
    })
  }, []);

  const selectOverlay = () => {
    console.log('selectOverlay called with type:', 'POLYLINE');
    dispatch(setTime(null));

    // 그리기 중이면 그리기를 취소합니다
    manager.cancel();

    manager.undo();

    // 클릭한 그리기 요소 타입을 선택합니다
    manager.select(window.kakao.maps.drawing.OverlayType.POLYLINE);
  };

  return (
    <CourseSelectWrapper>
      <Map />
      <CourseMaker onClick={() => selectOverlay()} id="courseMaker">
        코스 그리기
      </CourseMaker>
      <CourseList positions={positions} />
    </CourseSelectWrapper>
  );
};

export default CourseSelect;
