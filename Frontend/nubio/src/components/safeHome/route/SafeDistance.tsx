import { useSelector } from "react-redux";
import { SafeDistanceWrapper } from "../../../styles/SSafeHomePage";

const SafeDistance = () => {
  const timeData = useSelector((state: any) => state.enjoy.time);
  const nowTime = new Date();
  const nowHours = nowTime.getHours();
  const nowMinutes = nowTime.getMinutes();
  const arriveTimeHours = nowHours + Math.floor(timeData?.time / 60);
  const arriveTimeMinutes = nowMinutes + (timeData?.time % 60);

  const formattedArriveTimeMinutes = arriveTimeMinutes
    .toString()
    .padStart(2, "0");

  const arriveTime = `${arriveTimeHours}:${formattedArriveTimeMinutes}`;
  return (
    <SafeDistanceWrapper>
      <p id="shortName">안심 경로</p>
      <p id="shortTime">소요시간 : {timeData?.time}분</p>
      <p id="shortArriveTime">도착시간 : {arriveTime} 도착</p>
      <p id="shortDistance">거리 : {timeData?.dis}m</p>
    </SafeDistanceWrapper>
  );
};

export default SafeDistance;
