import { LocationItemWrapper, LocationText, LocationImg, TextWrapper } from "../../../../styles/SMyCoursePage";

type cityProps = {
    location: {
        name: string
        url: string
    }
}

const LocationItem = ({location}: cityProps) => {
    const {name, url} = location;
    return(
        <LocationItemWrapper>
            <LocationImg src={url} alt="" />
            <TextWrapper>
                <LocationText>{name}</LocationText>
            </TextWrapper>
        </LocationItemWrapper>
    );
}

export default LocationItem;