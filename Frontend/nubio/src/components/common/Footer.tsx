import { useState } from "react";
import {
  FooterWrapper,
  MenuToggle,
  MenuImg,
  MenuImgBox,
  MenuBox,
} from "../../styles/SFooter";
import SafeModal from "./menumodal/SafeModal";
import EnjoyModal from "./menumodal/EnjoyModal";
import ChatModal from "./menumodal/ChatModal";
import ProfileModal from "./menumodal/ProfileModal";
import React from "react";
import axios from "axios";
import RootState from "../../types/RootState";
import { useNavigate } from 'react-router-dom';

const Footer = () => {
  const navigate = useNavigate();
  const [active, setActive] = useState(false);
  const [safe, setSafe] = useState(false);
  const [enjoy, setEnjoy] = useState(false);
  const [chatactive, setChat] = useState(false);
  const [profile, setProfile] = useState(false);
  const menu = process.env.PUBLIC_URL + "/assets/menuModal/menu.png";
  const safeImg = process.env.PUBLIC_URL + "/assets/menuModal/menuSafe.png";
  const enjoyImg = process.env.PUBLIC_URL + "/assets/menuModal/menuEnjoy.png";
  const chat = process.env.PUBLIC_URL + "/assets/menuModal/chat.png";
  const profileImg =
    process.env.PUBLIC_URL + "/assets/menuModal/menuProfile.png";

  const toggleMenu = () => {
    setActive(!active);
    setSafe(false);
    setChat(false);
    setEnjoy(false);
    setProfile(false);
  };
  // const modalIn = async () => {
  //   try {
  //     const response = await axios.post(
  //       process.env.REACT_APP_SERVER_URL +
  //         "/chatting/v1/chatting/room/location",
  //       {
  //         latitude: window.myLatitude,
  //         longitude: window.myLongitude,
  //       }
  //     );
  //     console.log(response.data);
  //   } catch (error) {
  //     console.error("Error during the request:", error);
  //   }
  // };
  const handleClick = (e: any, operation: string) => {
    e.preventDefault();
    setSafe(false);
    setChat(false);
    setEnjoy(false);
    setProfile(false);
    if (operation === "safe") {
      setSafe(!safe);
    } else if (operation === "chat") {
      // modalIn();
      setChat(!chatactive);
    } else if (operation === "enjoy") {
      setEnjoy(!enjoy);
    } else if (operation === "profile") {
      setProfile(!profile);
    }
  };

  const items = document.querySelectorAll(".menuBox img");
  for (let i = 0; i < items.length; i++) {
    items[i].setAttribute("style", `position: absolute;`);
  }

  return (
    <FooterWrapper>
      <MenuToggle className={`tab ${active ? "active" : ""}`}>
        <MenuImgBox>
          <MenuImg src={menu} alt="" onClick={toggleMenu} />
        </MenuImgBox>
        <MenuBox className="menuBox">
          <div
            className={`icon ${active ? "active" : ""}`}
            style={{
              transform: `${
                active ? "translate(12rem, 2rem) scale(1)" : "scale(0)"
              }`,
            }}
            onClick={(e) => handleClick(e, "chat")}
          >
            <img src={chat} alt="" />
          </div>
          <div
            className={`icon ${active ? "active" : ""}`}
            style={{
              transform: `${
                active ? "translate(7rem, 4rem) scale(1)" : "scale(0)"
              }`,
            }}
            onClick={(e) => handleClick(e, "safe")}
          >
            <img src={safeImg} alt="" />
          </div>
          <div
            className={`icon ${active ? "active" : ""}`}
            style={{
              transform: `${
                active ? "translate(3.5rem, 7.5rem) scale(1)" : "scale(0)"
              }`,
            }}
            onClick={(e) => handleClick(e, "enjoy")}
          >
            <img src={enjoyImg} alt="" />
          </div>
          <div
            className={`icon ${active ? "active" : ""}`}
            style={{
              transform: `${
                active ? "translate(1rem, 12rem) scale(1)" : "scale(0)"
              }`,
            }}
            onClick={() => navigate("/profile")}
            // onClick={(e) => handleClick(e, "profile")}
          >
            <img src={profileImg} alt="" />
          </div>
        </MenuBox>
      </MenuToggle>
      {safe && <SafeModal setActive={() => setSafe(false)} />}
      {enjoy && <EnjoyModal setActive={() => setEnjoy(false)} />}
      {chatactive && <ChatModal setActive={() => setChat(false)} />}
      {profile && <ProfileModal setActive={() => setProfile(false)} />}
    </FooterWrapper>
  );
};

export default Footer;
