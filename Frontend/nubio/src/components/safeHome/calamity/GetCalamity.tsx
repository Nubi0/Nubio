import axios from "axios";

const GetCalamity = () => {
  const getCalamity = () => {
    axios
      .get("https://nubi0.com/api/safe/v1/safe/check")
      .then((res) => {
        console.log(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return;
};
export default GetCalamity;
