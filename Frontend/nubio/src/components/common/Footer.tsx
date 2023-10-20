import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import { FooterWrapper, MenuToggle, MenuImg, MenuImgBox, MenuBox } from "../../styles/SFooter";
import SafeModal from './menumodal/SafeModal';

const Footer = () => {
  const [active, setActive] = useState(false);
  const [safe, setSafe] = useState(false);
  const menu = process.env.PUBLIC_URL + '/assets/menu/menu.png';
  const safeImg = process.env.PUBLIC_URL + '/assets/menu/menuSafe.png';
  const enjoyImg = process.env.PUBLIC_URL + '/assets/menu/menuEnjoy.png';
  const chat = process.env.PUBLIC_URL + '/assets/menu/chat.png';
  const profileImg = process.env.PUBLIC_URL + '/assets/menu/menuProfile.png';

  const toggleMenu = () => {
    setActive(!active);
  }
  const handleClick = (e: any, operation: string) => {
    e.preventDefault()
    if(operation === 'safe') {
      setSafe(!safe);
    }
  }

  const items = document.querySelectorAll('.menuBox img');
  for(let i = 0; i < items.length; i++) {
    items[i].setAttribute('style', `position: absolute;`)
  }

  return (
    <FooterWrapper>
      <MenuToggle  className={`tab ${active ? 'active' : ''}`}>
        <MenuImgBox>
          <MenuImg src={menu} alt="" onClick={toggleMenu} />
        </MenuImgBox>
        <MenuBox className='menuBox'></MenuBox>
        <div className={`icon ${active ? 'active' : ''}`} style={{transform: `${active ? 'translate(10rem, -3rem) scale(1)' : 'scale(0)'}`}}>
          <img src={chat} alt="" />
        </div>
        <div className={`icon ${active ? 'active' : ''}`} style={{transform: `${active ? 'translate(5rem, -3rem) scale(1)' : 'scale(0)'}`}}>
          <img src={safeImg} alt="" />
        </div>
        <div className={`icon ${active ? 'active' : ''}`} style={{transform: `${active ? 'translate(2rem, -2rem) scale(1)' : 'scale(0)'}`}}>
          <img src={enjoyImg} alt="" />
        </div>
        <div className={`icon ${active ? 'active' : ''}`} style={{transform: `${active ? 'translate(0rem, 0rem) scale(1)' : 'scale(0)'}`}}>
          <img src={profileImg} alt="" />
        </div>
      </MenuToggle>
      {safe && <SafeModal />}
    </FooterWrapper>
  );
};

export default Footer;
