import { useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
const KaKaoLogin = () => {
  const navigate = useNavigate();
  useEffect(() => {
    const code = new URL(window.location.href).searchParams.get("code");
    const redirectUrl = window.location.origin + "/oauth/kakao";
    axios
      .post(
        `${process.env.REACT_APP_SERVER_URL}/start/v1/oauth/kakao/callback`,
        {
          code,
          redirectUrl,
        },
      )
      .then((res) => {
        const { accessToken, refreshToken, refreshTokenExpireTime } =
          res.data.data;
        axios.defaults.headers.common[
          "Authorization"
        ] = `Bearer ${accessToken}`;
        localStorage.setItem("refreshToken", refreshToken);
        localStorage.setItem("refreshTokenExpireTime", refreshTokenExpireTime);
        navigate("/");
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);
  return <></>;
};

export default KaKaoLogin;
