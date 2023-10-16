export interface propsType {
  searchKeyword: string;
}
export interface SetDirectionProps {
  map: any;
}

export interface placeType {
  place_name: string;
  road_address_name: string;
  address_name: string;
  phone: string;
  place_url: string;
  length: number;
  x: string;
  y: string;
}

export interface searchType {
  keyword: string;
}
interface SearchBarProps {
  searchPlaces: (query: string) => void;
  setListIsOpen: (isOpen: boolean) => void;
  setFindRouteOpen: (isOpen: boolean) => void;
}
