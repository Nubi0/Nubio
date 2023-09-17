import { useEffect } from 'react';
import { CourseSelectWrapper, CourseMaker } from '../../../../styles/SCourseSelectPage';
import CoursePinList from './CourseList';
import CourseResult from './CourseResult';
import { useDispatch, useSelector } from 'react-redux';
import Map from '../../../common/map/Map';
import { setPosition, setTime } from '../../../../redux/slice/EnjoySlice';

declare global {
  interface Window {
    kakao: any;
  }
}

const CourseSelect = ({ setModal }: any) => {
  const timeData = useSelector((state: any) => state.enjoy.time);
  const dispatch = useDispatch();
  const dummy1 = process.env.PUBLIC_URL + '/assets/dummy/dummy1.jpg';
  const dummyUrl = process.env.PUBLIC_URL + '/assets/dummy/dummy2.jpg';
  const manager = window.kakaoManager;
  console.log(manager);
  var positions = [
    {
      title: '카카오',
      lat: 33.450705,
      lng: 126.570677,
      img_url: dummy1,
    },
    {
      title: '생태연못',
      lat: 33.450936,
      lng: 126.569477,
      img_url: dummyUrl,
    },
    {
      title: '텃밭',
      lat: 33.450879,
      lng: 126.56994,
      img_url: dummyUrl,
    },
    {
      title: '근린공원',
      lat: 33.451393,
      lng: 126.570738,
      img_url: dummyUrl,
    },
  ];

  // 커스텀 마커 생성

  useEffect(() => {
    dispatch(setPosition(positions));
    dispatch(setTime(null));
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
      <CoursePinList positions={positions} />
      {timeData && <CourseResult data={timeData} setModal={setModal} />}
    </CourseSelectWrapper>
  );
};

export default CourseSelect;
