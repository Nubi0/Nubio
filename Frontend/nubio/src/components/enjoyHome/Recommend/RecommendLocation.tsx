import { Title, Address, RecommendLoactionWrapper, AddressWrapper, AddressSelect } from "../../../styles/SRecommendPage";

const RecommendLocation = () => {
    return(
        <RecommendLoactionWrapper>
            <Title>추천<span>받고 싶은 지역</span></Title>
            <AddressWrapper>
                <Address>주소주소주소</Address>
                <AddressSelect>주소 선택</AddressSelect>
            </AddressWrapper>
        </RecommendLoactionWrapper>
    );
}

export default RecommendLocation;