import { useSelector } from "react-redux";
import { ShortDistanceWrapper } from "../../../styles/SSafeHomePage";

const ShortDistance = () => {
  const timeData = useSelector((state: any) => state.enjoy.time);
  const nowTime = new Date();
  const nowHours = nowTime.getHours();
  const nowMinutes = nowTime.getMinutes();
  const arriveTimeHours = nowHours + Math.floor(timeData.time / 60);
  const arriveTimeMinutes = nowMinutes + (timeData.time % 60);

  const formattedArriveTimeMinutes = arriveTimeMinutes
    .toString()
    .padStart(2, "0");

  const arriveTime = `${arriveTimeHours}:${formattedArriveTimeMinutes}`;

  console.log(nowTime);
  return (
    <ShortDistanceWrapper>
      <p id="shortName">최단경로</p>
      <p id="shortTime">소요시간 : {timeData.time}분</p>
      <p id="shortArriveTime">도착시간 : {arriveTime} 도착</p>
      <p id="shortDistance">거리 : {timeData.dis}m</p>
    </ShortDistanceWrapper>
  );
};
export default ShortDistance;
