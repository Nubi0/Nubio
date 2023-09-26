import { useState, useEffect } from 'react';
import {
  CourseResultWrapper,
  Title,
  Data,
  ModalOpen,
} from '../../../../styles/SCourseSelectPage';
import { useDispatch } from 'react-redux';
import { setCoursePoint } from '../../../../redux/slice/EnjoySlice';

const CourseResult = ({ data, setModal }: { data: t_d_DataProps; setModal: any }) => {
  const { time, type, dis } = data;
  const hours = new Date().getHours();
  const min = new Date().getMinutes();
  const [calTime, setCalTime] = useState({
    hour: 0,
    min: 0,
  });
  const dispatch = useDispatch();

  useEffect(() => {
    if (type === '시') {
      const cal_h = hours + time;
      setCalTime({
        hour: cal_h,
        min: time,
      });
    } else {
      let cal_m = min + time;
      if (cal_m >= 60) {
        const cal_h = hours + 1;
        cal_m -= 60;
        setCalTime({
          hour: cal_h,
          min: cal_m,
        });
      } else {
        setCalTime({
          hour: hours,
          min: cal_m,
        });
      }
    }
  }, []);

  const handleSave = () => {
    setModal();
    dispatch(setCoursePoint(window.kakaoManager.getData().polyline[0].points))
  }

  return (
    <CourseResultWrapper>
      <Title>나만의 경로</Title>
      <Data>
        <div>
          도보 {time}
          {type}
        </div>
        <div>
          {calTime.hour} : {calTime.min} 도착
        </div>
        <div>{dis}m</div>
      </Data>
      <ModalOpen onClick={handleSave}>저장</ModalOpen>
    </CourseResultWrapper>
  );
};

export default CourseResult;
