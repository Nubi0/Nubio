import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect } from "react";

const NearbyShelter = () => {
  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude,
    ) || null;
  const getNearbyShelter = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/nearwith/safe-shelter?longitude=${longitude}&latitude=${latitude}&distance=1&type=school`,
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    getNearbyShelter();
  });
  return null;
};
export default NearbyShelter;
