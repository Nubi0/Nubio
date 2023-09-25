import axios from "axios";
import { useEffect } from "react";
import { useSelector } from "react-redux";

const GetCalamity = () => {
  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude
    ) || null;
  const getCalamity = () => {
    axios
      .post("https://nubi0.com/safe/v1/safe/check", {
        latitude,
        longitude,
      })
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    getCalamity();
  });
  return null;
};
export default GetCalamity;
