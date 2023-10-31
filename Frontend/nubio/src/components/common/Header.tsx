import { HeaderWrapper } from "../../styles/SHeader";
import { useNavigate } from "react-router-dom";

const Header = () => {
    const logoUrl = process.env.PUBLIC_URL + '/assets/nubio-small.png';
    const navigate = useNavigate();
    return(
        <HeaderWrapper>
            <img src={logoUrl} onClick={() => navigate('/')} alt="logo" />
        </HeaderWrapper>
    );
};

export default Header;