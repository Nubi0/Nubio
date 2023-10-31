import { useNavigate } from "react-router-dom";
import { LocationItemWrapper, LocationText, LocationImg, TextWrapper } from "../../../../styles/SMyCoursePage";
import { useDispatch } from "react-redux";
import { setRegion } from "../../../../redux/slice/EnjoySlice";

type cityProps = {
    location: {
        name: string
        url: string
    }
}

const LocationItem = ({location}: cityProps) => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const {name, url} = location;
    const handleLocation = () => {
        dispatch(setRegion(name));
        navigate('/enjoy/all');
    }
    return(
        <LocationItemWrapper onClick={handleLocation}>
            <LocationImg src={url} alt="" />
            <TextWrapper>
                <LocationText>{name}</LocationText>
            </TextWrapper>
        </LocationItemWrapper>
    );
}

export default LocationItem;