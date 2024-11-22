import * as React from "react"
import Svg, { Path } from "react-native-svg"
const SaveIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={24}
    height={24}
    fill="none"
    {...props}
  >
    <Path
      stroke="#A6A6A6"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2.333}
      d="M21.5 8.167V24.5l-7-4.667-7 4.667V8.167A4.667 4.667 0 0 1 12.167 3.5h4.666A4.667 4.667 0 0 1 21.5 8.167Z"
    />
  </Svg>
)
export { SaveIcon }
