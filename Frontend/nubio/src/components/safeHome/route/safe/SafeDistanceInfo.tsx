import { useSelector } from "react-redux";
import { SafeDistanceWrapper } from "../../../../styles/SRoute";

const SafeDistance = () => {
  const timeData = useSelector((state: any) => state.safe.safeTime);
  const nowTime = new Date();
  const nowHours = nowTime.getHours();
  const nowMinutes = nowTime.getMinutes();
  let arriveTimeHours = nowHours + Math.floor(timeData?.time / 60);
  if (arriveTimeHours >= 24) {
    arriveTimeHours = Math.abs(arriveTimeHours - 24);
  }
  let arriveTimeMinutes = nowMinutes + (timeData?.time % 60);
  if (arriveTimeMinutes >= 60) {
    arriveTimeHours += 1;
    arriveTimeMinutes = Math.abs(arriveTimeMinutes - 60);
  }
  const formattedArriveTimeHours = arriveTimeHours.toString().padStart(2, "0");
  const formattedArriveTimeMinutes = arriveTimeMinutes
    .toString()
    .padStart(2, "0");

  const arriveTime = `${formattedArriveTimeHours}:${formattedArriveTimeMinutes}`;
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
