import { useLocation } from "react-router-dom";
import { FooterWrapper } from "../../styles/SFooter";

const Footer = () => {
    const { pathname } = useLocation();
    const homeImg = process.env.REACT_APP_HOME_URL;
    const enjoyImg = process.env.REACT_APP_ENJOY_URL;
    const safeImg = process.env.REACT_APP_SAFE_URL;
    return(
        <FooterWrapper>
            <img src={pathname === '/enjoy' ? enjoyImg : safeImg} alt="" />
            <img src={homeImg} alt="" />
            <img src={safeImg} alt="" />
        </FooterWrapper>
    );
}

export default Footer;