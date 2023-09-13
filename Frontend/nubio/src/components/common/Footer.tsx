import { useLocation, useNavigate } from "react-router-dom";
import { FooterWrapper } from "../../styles/SFooter";

const Footer = () => {
  const { pathname } = useLocation();
  const enjoyImg = process.env.PUBLIC_URL + "/assets/enjoy.svg";
  const safeImg = process.env.PUBLIC_URL + "/assets/safe.svg";
  const homeImg = process.env.PUBLIC_URL + "/assets/home.svg";
  const profileImg = process.env.PUBLIC_URL + "/assets/profile.svg";
  const navigate = useNavigate();
  return (
    <FooterWrapper>
      <img src={pathname === "/enjoy" ? enjoyImg : safeImg} alt="" />
      <img
        src={homeImg}
        alt=""
        onClick={() => {
          navigate("/");
        }}
      />
      <img
        src={profileImg}
        alt=""
        onClick={() => {
          navigate("/profile");
        }}
      />
    </FooterWrapper>
  );
};

export default Footer;
