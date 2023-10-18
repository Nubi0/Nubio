import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import { FooterWrapper, MenuToggle } from "../../styles/SFooter";

const Footer = () => {
  const [active, setActive] = useState(false);
  const safeImg = process.env.PUBLIC_URL + "/assets/menu/menuSafe.svg";
  const menu = process.env.PUBLIC_URL + "/assets/menu/menu.svg";
  const profileImg = process.env.PUBLIC_URL + "/assets/menu/menuProfile.svg";
  const enjoyImg = process.env.PUBLIC_URL + "/assets/menu/menuEnjoy.svg"
  const chat = process.env.PUBLIC_URL + "/assets/menu/chat.svg";
  const navigate = useNavigate();
  const toggleMenu = () => {
    setActive(!active);
  }

  return (
    <FooterWrapper>
      <MenuToggle className={`tab ${active ? "active" : ""}`} style={{ position: 'relative'}}>
        <img src={menu} alt="menu" onClick={toggleMenu} />
        <div className="cbg1"></div>
        <div className="cbg2"></div>
        <div>
          <ul>
            <li>
              <img src={chat} alt="" />
            </li>
            <li>
              <img src={safeImg} alt="" />
            </li>
            <li>
              <img src={enjoyImg} alt="" />
            </li>
            <li>
              <img src={profileImg} alt="" />
            </li>
          </ul>
        </div>
      </MenuToggle>
    </FooterWrapper>
  );
};

export default Footer;
