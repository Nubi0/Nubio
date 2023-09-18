interface PlaceType {
  place_name: string;
  road_address_name: string;
  address_name: string;
  phone: string;
  place_url: string;
  length: number;
}

const SearchItem = ({
  places,
  index,
}: {
  places: PlaceType;
  index: number;
}) => {
  return (
    <div className="info">
      <div className="name">
        <h5 className="info-item place-name">
          {index + 1}. {places.place_name}
        </h5>
        <a id="homePage" href={places.place_url}>
          상세보기
        </a>
      </div>
      {places.road_address_name ? (
        <span className="address">
          {places.road_address_name} {places.address_name}
        </span>
      ) : (
        <span className="address">{places.address_name}</span>
      )}
      <span className="tel">{places.phone}</span>
      <span className="direction">
        <button>출발</button>
        <button>도착</button>
      </span>
    </div>
  );
};

export default SearchItem;
