import { HeaderWrapper } from "../../styles/SHeader";

const Header = () => {
    const logoUrl = process.env.PUBLIC_URL + '/assets/nubio-small.png';
    return(
        <HeaderWrapper>
            <img src={logoUrl} />
        </HeaderWrapper>
    );
};

export default Header;