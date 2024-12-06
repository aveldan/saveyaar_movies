import React from "react";
import Animated, { AnimatedProps, interpolate, useAnimatedStyle } from "react-native-reanimated";
import { Image, Pressable, View } from "react-native";
import { BlurView } from "expo-blur";
import { useSafeAreaInsets } from "react-native-safe-area-context";

import { NotificationIcon } from "@/assets/svg";
import { styles } from "@/styles";

interface AnimatedHeaderProps {
    headerAnimatedProps: AnimatedProps<any>;
    scrollDirection: Animated.SharedValue<number>;
    handleProfile: () => void,
    handleNotification: () => void,
}

const AnimatedBlurView = Animated.createAnimatedComponent(BlurView);

export default function AnimatedHeader({headerAnimatedProps, scrollDirection, handleProfile, handleNotification}: AnimatedHeaderProps) {

    const insets = useSafeAreaInsets();
    
    const headerTitleStyle = useAnimatedStyle(() => {
        return {
            transform: [
                {
                    scale: interpolate(
                        scrollDirection.value,
                        [0, 1],
                        [1, 0.96],
                        'clamp'
                    )
                }
            ]
        };
    });

    const userImageSource = "https://s3-alpha-sig.figma.com/img/afb9/3a50/98d2fa0a32dceb793f76f36a8d18243c?Expires=1733702400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=MZF~DHddnupR5C1q5p4qRYyD9XfRKr9NqJrxIFv7sm1WDk-zmvqZkjZG9njsRSxjhMfqA1CqDlDpYJwlDa0yh1KhnaMCSqYMkmKvujSBAKVAXsNGTBDaOdwtY1dD24iJs0Ox~Z~Hj4ljQ8tey-gY872mRYtxfu0weGsySYvFoGOclY5TKhBoMcwAN-QiZ~iiCze-c7BFY1X0uxMqXlliBW9nbgt1jYHJGP5SVf7wn6A9SiRre0vQxtKojnnml8kzZ2U6Tvqo~1~VNJjrZ~TgtZ-dUh-krIEMiXOkxqHrhXAv2DZvm3dscwIWDUzRoNitGBV7lhhp6PX2RxByP2JE2g__";
    
    const headerAnimatedStyle = useAnimatedStyle(() => {
        return {
            opacity: interpolate(
                scrollDirection.value,
                [0, 0.5, 1],
                [1, 0.8, 0],
                'clamp'
            ),
            transform: [
                {
                    translateY: interpolate(
                        scrollDirection.value,
                        [0, 1],
                        [0, -40],
                        'clamp'
                    )
                }
            ],
            overflow: 'hidden',
            height: interpolate(
                scrollDirection.value,
                [0, 1],
                [47, 0],
                'clamp'
            ),
        };
    });

    return (
        <Animated.View style={[styles.header]}>
            <AnimatedBlurView 
                tint="systemThickMaterialDark"
                style={[styles.blurContainer, { paddingTop: insets.top }]}
                animatedProps={headerAnimatedProps}
            >
                <Animated.View style={[styles.headerTitleContainer, headerTitleStyle]}>
                    <Pressable
                        style={styles.profileBorder}
                        onPress={handleProfile}
                    >
                        <Image style={styles.profileImg} source={{uri: userImageSource}}/>
                    </Pressable>
                    <Pressable onPress={handleNotification}>
                        <NotificationIcon width={28} height={28} />
                    </Pressable>
                </Animated.View>
            </AnimatedBlurView>
        </Animated.View>
    )
};