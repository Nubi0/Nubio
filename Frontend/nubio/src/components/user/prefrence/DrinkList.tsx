import { DrinkWrapper, IconWrapper } from "../../../styles/SSignUpPage";

type DrinkImages = { [key: string]: string };
type DrinkListProps = {
  handleImageClick: (name: string) => void;
  selectedImages: string[];
};
const drinkImages: DrinkImages = {
  beer: process.env.PUBLIC_URL + "/assets/drink/beer.png",
  cocktail: process.env.PUBLIC_URL + "/assets/drink/cocktail.png",
  coffee: process.env.PUBLIC_URL + "/assets/drink/coffee.png",
  iceCream: process.env.PUBLIC_URL + "/assets/drink/iceCream.png",
  riceWine: process.env.PUBLIC_URL + "/assets/drink/riceWine.png",
  soju: process.env.PUBLIC_URL + "/assets/drink/soju.png",
  wine: process.env.PUBLIC_URL + "/assets/drink/wine.png",
};

const DrinkList: React.FC<DrinkListProps> = ({
  handleImageClick,
  selectedImages,
}) => {
  return (
    <DrinkWrapper>
      <h1>마실거리</h1>
      <IconWrapper>
        {Object.entries(drinkImages).map(([name, src]) => (
          <img
            key={name}
            src={src}
            alt={name}
            id={selectedImages.includes(name) ? "check" : "unCheck"}
            onClick={() => handleImageClick(name)}
          />
        ))}
      </IconWrapper>
    </DrinkWrapper>
  );
};

export default DrinkList;
