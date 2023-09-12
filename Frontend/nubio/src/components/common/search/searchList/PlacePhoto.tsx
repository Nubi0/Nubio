import { PlacePhotoWrapper } from "../../../../styles/SSearchBar";

const PlacePhoto = ({ photo }: { photo: photoProps }) => {
  return (
    <PlacePhotoWrapper>
      <img src={photo.url} alt="" />
      <img src={photo.url} alt="" />
    </PlacePhotoWrapper>
  );
};

export default PlacePhoto;
