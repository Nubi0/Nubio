import { HeaderWrapper } from "../../styles/SHeader";

const Header = () => {
    const logoUrl = process.env.REACT_APP_SMALL_LOGO_URL;
    return(
        <HeaderWrapper>
            <img src={logoUrl} />
        </HeaderWrapper>
    );
};

export default Header;