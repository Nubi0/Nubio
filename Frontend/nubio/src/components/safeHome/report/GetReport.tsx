import axios from "axios";
import { useSelector } from "react-redux";
import { useEffect } from "react";

const GetReport = () => {
  const latitude =
    useSelector((state: { map: { latitude: string } }) => state.map.latitude) ||
    null;
  const longitude =
    useSelector(
      (state: { map: { longitude: string } }) => state.map.longitude,
    ) || null;
  console.log(latitude);
  console.log(longitude);
  const getReport = () => {
    axios
      .get(
        `https://nubi0.com/safe/v1/safe/report?longitude=${longitude}&latitude=${latitude}`,
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  useEffect(() => {
    getReport();
  });
  return null;
};
export default GetReport;
