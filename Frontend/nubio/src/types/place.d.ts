type placeProps = {
  place: {
    id: string;
    address_name: string;
    category_group_code: string;
    category_group_name: string;
    phone: string;
    place_name: string;
    place_url: string;
    img_url: string;
    road_address_name: string;
    x: string;
    y: string;
  };
};

interface placeListProps {
  placeList: () => void;
}

type infoProps = {
  info: {
    addres_name: string;
    phone: string;
    place_name: string;
    place_url: string;
  };
};
