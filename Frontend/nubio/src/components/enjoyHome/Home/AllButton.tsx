import { AllButtonWrapper, AllButtonImg } from "../../../styles/SEnjoyHomePage";

const AllButton = () => {
    const AllButtonUrl = process.env.REACT_APP_ALL_URL;
    return(
        <AllButtonWrapper>
            <button>
                <AllButtonImg src={AllButtonUrl} />
            </button>
        </AllButtonWrapper>
    );
}

export default AllButton;