import { useNavigate } from "react-router-dom";
import { LocationItemWrapper, LocationText, LocationImg, TextWrapper } from "../../../../styles/SMyCoursePage";

type cityProps = {
    location: {
        name: string
        url: string
    }
}

const LocationItem = ({location}: cityProps) => {
    const navigate = useNavigate();
    const {name, url} = location;
    return(
        <LocationItemWrapper onClick={() => {navigate('/mycourse/select')}}>
            <LocationImg src={url} alt="" />
            <TextWrapper>
                <LocationText>{name}</LocationText>
            </TextWrapper>
        </LocationItemWrapper>
    );
}

export default LocationItem;