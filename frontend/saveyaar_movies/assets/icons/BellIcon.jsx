import * as React from "react";
import Svg, { Path } from "react-native-svg";
const BellIcon = (props) => (
  <Svg
    width={16}
    height={17}
    viewBox="0 0 16 17"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <Path
      d="M8.33329 11.9368H2.66663C3.02992 11.7311 3.33983 11.443 3.57142 11.0956C3.803 10.7482 3.94979 10.3513 3.99996 9.93685V7.93685C4.03967 7.0909 4.30883 6.2717 4.77859 5.56705C5.24836 4.8624 5.90102 4.29887 6.66663 3.93685C6.66663 3.58323 6.8071 3.24409 7.05715 2.99404C7.3072 2.74399 7.64634 2.60352 7.99996 2.60352C8.35358 2.60352 8.69272 2.74399 8.94277 2.99404C9.19282 3.24409 9.33329 3.58323 9.33329 3.93685C10.0989 4.29887 10.7516 4.8624 11.2213 5.56705C11.6911 6.2717 11.9603 7.0909 12 7.93685V8.60352M5.99996 11.9368V12.6035C5.99989 12.895 6.06353 13.1829 6.18642 13.4472C6.30931 13.7115 6.48848 13.9457 6.71138 14.1335C6.93428 14.3213 7.19554 14.4581 7.47685 14.5343C7.75816 14.6106 8.05274 14.6244 8.33996 14.5748M10.6666 13.2702H14.6666M12.6666 11.2702V15.2702"
      stroke="white"
      strokeOpacity={0.6}
      strokeWidth={1.33333}
      strokeLinecap="round"
      strokeLinejoin="round"
    />
  </Svg>
);
export { BellIcon };