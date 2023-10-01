import { useSelector } from "react-redux";
import { ShortDistanceWrapper } from "../../../../styles/SSafeHomePage";

const ShortDistance = () => {
  const timeData = useSelector((state: any) => state.map.safeTime);
  const nowTime = new Date();
  const nowHours = nowTime.getHours();
  const nowMinutes = nowTime.getMinutes();
  let arriveTimeHours = nowHours + Math.floor(timeData?.time / 60);
  if (arriveTimeHours >= 60) {
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
    <ShortDistanceWrapper>
      <p id="shortName">최단 경로</p>
      <p id="shortTime">소요시간 : {timeData?.time}분</p>
      <p id="shortArriveTime">도착시간 : {arriveTime} 도착</p>
      <p id="shortDistance">거리 : {timeData?.dis}m</p>
    </ShortDistanceWrapper>
  );
};
export default ShortDistance;
