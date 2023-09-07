import { PlayWrapper, IconWrapper } from "../../../styles/SSignUpPage";

type PlayImages = { [key: string]: string };
type PlayListProps = {
  handleImageClick: (name: string) => void;
  selectedImages: string[];
};

const playImages: PlayImages = {
  amusementPark: process.env.PUBLIC_URL + "/assets/play/amusementPark.png",
  craftShop: process.env.PUBLIC_URL + "/assets/play/craftShop.png",
  culturalHeritage:
    process.env.PUBLIC_URL + "/assets/play/culturalHeritage.png",
  dice: process.env.PUBLIC_URL + "/assets/play/dice.png",
  karaoke: process.env.PUBLIC_URL + "/assets/play/karaoke.png",
  movie: process.env.PUBLIC_URL + "/assets/play/movie.png",
  sea: process.env.PUBLIC_URL + "/assets/play/sea.png",
};

const PlayList: React.FC<PlayListProps> = ({
  handleImageClick,
  selectedImages,
}) => {
  // 이미지

  return (
    <PlayWrapper>
      <h1>놀거리</h1>
      <IconWrapper>
        {Object.entries(playImages).map(([name, src]) => (
          <img
            key={name}
            src={src}
            alt={name}
            id={selectedImages.includes(name) ? "check" : "unCheck"}
            onClick={() => handleImageClick(name)}
          />
        ))}
      </IconWrapper>
    </PlayWrapper>
  );
};

export default PlayList;
