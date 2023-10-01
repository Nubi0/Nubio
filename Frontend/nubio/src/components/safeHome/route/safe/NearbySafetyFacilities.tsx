import axios from "axios";
import { useEffect } from "react";
import { useSelector } from "react-redux";

const NearbySafetyFacilities = () => {
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude,
    ) || null;

  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const getNearbySafetyFacilities = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/nearwith/safe-facility/all?longitude=${longitude}&latitude=${latitude}&distance=1`,
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    getNearbySafetyFacilities();
  });
  return <div></div>;
};
export default NearbySafetyFacilities;
