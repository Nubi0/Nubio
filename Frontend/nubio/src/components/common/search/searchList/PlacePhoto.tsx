import { PlacePhotoWrapper } from "../../../../styles/SSearchBar";

type photoProps = {
  url: string;
};

const PlacePhoto = ({ photo }: { photo: photoProps }) => {
  return (
    <PlacePhotoWrapper>
      <img src={photo.url} alt="" />
      <img src={photo.url} alt="" />
    </PlacePhotoWrapper>
  );
};

export default PlacePhoto;
