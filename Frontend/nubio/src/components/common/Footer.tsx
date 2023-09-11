import { useLocation } from 'react-router-dom';
import { FooterWrapper } from '../../styles/SFooter';

const Footer = () => {
  const { pathname } = useLocation();
  const enjoyImg = process.env.PUBLIC_URL + '/assets/enjoy.svg';
  const safeImg = process.env.PUBLIC_URL + '/assets/safe.svg';
  const homeImg = process.env.PUBLIC_URL + '/assets/home.svg';
  const profileImg = process.env.PUBLIC_URL + '/assets/profile.svg';
  return (
    <FooterWrapper>
      <img src={pathname === '/enjoy' ? enjoyImg : safeImg} alt="" />
      <img src={homeImg} alt="" />
      <img src={profileImg} alt="" />
    </FooterWrapper>
  );
};

export default Footer;
