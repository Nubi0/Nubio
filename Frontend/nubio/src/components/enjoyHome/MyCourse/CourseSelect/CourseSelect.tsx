import { useEffect, useState } from 'react';
import {
  CourseSelectWrapper,
  CourseMaker,
  MapWrapper,
} from '../../../../styles/SCourseSelectPage';
import CoursePinList from './CourseList';
import CourseResult from './CourseResult';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../../../../types/RootState';
import Map from '../../../common/map/Map';

declare global {
  interface Window {
    kakao: any;
  }
}

const CourseSelect = ({setModal}: any) => {
  const dispatch = useDispatch();
  let drawnData: any = null;
  // const [manager, setManager] = useState<any>(null);
  const [timeData, setTimeData] = useState<t_d_DataProps | null>(null)
  const dummy1 = process.env.PUBLIC_URL + '/assets/dummy/dummy1.jpg'
  const dummyUrl = process.env.PUBLIC_URL + '/assets/dummy/dummy2.jpg';
  const manager = useSelector((state: any) => state.enjoy.manager)
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

  return (
    <CourseSelectWrapper>
      <Map />
      <CourseMaker onClick={() => selectOverlay('POLYLINE')} id="courseMaker">
        코스 그리기
      </CourseMaker>
      <CoursePinList positions={positions} />
      {timeData && <CourseResult data={timeData} setModal={setModal} />}
    </CourseSelectWrapper>
  );
};

export default CourseSelect;
