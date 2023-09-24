import axios from "axios";
import { useSelector } from "react-redux";

const GetReport = () => {
  const latitude =
    useSelector(
      (state: { MapSlice: { latitude: string } }) => state.MapSlice.latitude,
    ) || null;
  const longitude =
    useSelector(
      (state: { MapSlice: { longitude: string } }) => state.MapSlice.longitude,
    ) || null;
  const getReport = () => {
    axios
      .get(
        `https://nubi0.com/api/v1/safe/report?longitude=${longitude}&latitude=${latitude}`,
      )
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return;
};
export default GetReport;
