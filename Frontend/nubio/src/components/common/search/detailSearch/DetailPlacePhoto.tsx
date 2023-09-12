import { DetailPlacePhotoWrapper } from "../../../../styles/SSearchBar";

const DetailPlacePhoto = ({ photo }: { photo: photoProps }) => {
  return (
    <DetailPlacePhotoWrapper>
      <img src={photo.url} />
      <img src={photo.url} />
    </DetailPlacePhotoWrapper>
  );
};

export default DetailPlacePhoto;
