import { AllButtonWrapper, AllButtonImg, ContentWrapper, FitText, Content } from "../../../styles/SEnjoyHomePage";

const AllButton = () => {
    const AllButtonUrl = process.env.REACT_APP_ALL_URL;
    return(
        <AllButtonWrapper>
            <ContentWrapper>
                <FitText>
                    FIT
                </FitText>
                <Content>당신의 취향에 <br /> 맞는 코스에요.</Content>
            </ContentWrapper>
            <button>
                <AllButtonImg src={AllButtonUrl} />
            </button>
        </AllButtonWrapper>
    );
}

export default AllButton;