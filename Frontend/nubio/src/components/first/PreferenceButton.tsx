import { PreferenceButtonWrapper } from "../../styles/SHomePage";

const PreferenceButton = ({openModal}: {openModal: any}) => {
    return(
        <PreferenceButtonWrapper>
            <button onClick={openModal}>취향 설정</button>
        </PreferenceButtonWrapper>
    )   
}

export default PreferenceButton;