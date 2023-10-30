import { AllButtonWrapper, AllButtonImg, ContentWrapper, FitText, Content } from "../../../styles/SEnjoyHomePage";
import { useNavigate } from "react-router-dom";

const AllButton = () => {
    const AllButtonUrl = process.env.PUBLIC_URL + '/assets/all.svg';
    const navigate = useNavigate();
    return(
        <AllButtonWrapper>
            <ContentWrapper>
                <FitText>
                    FIT
                </FitText>
                <Content>당신의 취향에 <br /> 맞는 코스에요.</Content>
            </ContentWrapper>
            <button onClick={() => navigate('/enjoy/all/location')}>
                <AllButtonImg src={AllButtonUrl} alt="all" />
            </button>
        </AllButtonWrapper>
    );
}

export default AllButton;