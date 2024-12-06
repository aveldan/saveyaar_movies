import React from "react";
import { ScrollView, View, Image, ImageBackground, TouchableOpacity } from "react-native";
import { useRouter } from "expo-router";
import { usePathname } from "expo-router";

import HeaderComponent from "@/components/HeaderComponent";
import { styles } from "@/styles/userProfile";
import TabScreenWrapper from "@/components/TabScreenWrapper";
import TextComponent from "@/components/TextComponents";
import { indiaFlag } from "@/assets/images";
import { FilePensilIcon, HelpIcon, LogoutIcon, PrefrencesIcon, RightArrow, PurpleNotification } from "@/assets/svg";


const UserCard = () => {
    const userInfo = {
        imageSource: 'https://s3-alpha-sig.figma.com/img/afb9/3a50/98d2fa0a32dceb793f76f36a8d18243c?Expires=1733702400&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=MZF~DHddnupR5C1q5p4qRYyD9XfRKr9NqJrxIFv7sm1WDk-zmvqZkjZG9njsRSxjhMfqA1CqDlDpYJwlDa0yh1KhnaMCSqYMkmKvujSBAKVAXsNGTBDaOdwtY1dD24iJs0Ox~Z~Hj4ljQ8tey-gY872mRYtxfu0weGsySYvFoGOclY5TKhBoMcwAN-QiZ~iiCze-c7BFY1X0uxMqXlliBW9nbgt1jYHJGP5SVf7wn6A9SiRre0vQxtKojnnml8kzZ2U6Tvqo~1~VNJjrZ~TgtZ-dUh-krIEMiXOkxqHrhXAv2DZvm3dscwIWDUzRoNitGBV7lhhp6PX2RxByP2JE2g__',
        name: 'Bhavna Goyal',
        email: 'Bhavanagoyal@gmail.com',
        phone: '9246166177',
    };


    return (
        <View style={styles.headerViewStyle}>
            <Image style={styles.profileImg} source={{uri: userInfo.imageSource}}/>
            <View style={styles.info}>
                <TextComponent
                    text={userInfo.name}
                    size={20}
                    bold
                    style={styles.textStyle}
                    numberOfLine={2}
                />
                <TextComponent
                    text={userInfo.email}
                    size={10}
                    regular
                    style={styles.textStyle}
                    numberOfLine={2}
                />
                <TextComponent
                    text={"+91 "+userInfo.phone}
                    size={10}
                    regular
                    style={styles.textStyle}
                />
            </View>
            <ImageBackground source={indiaFlag} style={styles.flagImage}/>
        </View>
    );
}

const OptionsCard = () => {

    const icon_width = 24;
    const icon_height = 24;
    const text_size = 16;
    return (
        <View style={styles.rowViewStyle}>
            <TouchableOpacity 
                style={styles.optionViewStyle}
            >
                <View style={styles.iconViewStyle}>
                    <View style={{marginHorizontal: 10}}>
                        <PrefrencesIcon width={icon_width} height={icon_height}/>
                    </View>

                    <TextComponent
                        text="My Preferences"
                        size={text_size}
                        bold
                        style={[styles.headingStyle]}
                    />
                </View>

                <View style={styles.arrowViewStyle}>
                    <RightArrow />
                </View>
            </TouchableOpacity>

            <TouchableOpacity 
                style={styles.optionViewStyle}
            >
                <View style={styles.iconViewStyle}>
                    <View style={{marginHorizontal: 10}}>
                        <FilePensilIcon width={icon_width} height={icon_height}/>
                    </View>

                    <TextComponent
                        text="Edit Subscriptions"
                        size={text_size}
                        bold
                        style={[styles.headingStyle]}
                    />
                </View>

                <View style={styles.arrowViewStyle}>
                    <RightArrow />
                </View>
            </TouchableOpacity>

            <TouchableOpacity 
                style={styles.optionViewStyle}
            >
                <View style={styles.iconViewStyle}>
                    <View style={{marginHorizontal: 10}}>
                        <PurpleNotification width={icon_width} height={icon_height}/>
                    </View>

                    <TextComponent
                        text="Notifications"
                        size={text_size}
                        bold
                        style={[styles.headingStyle]}
                    />
                </View>

                <View style={styles.arrowViewStyle}>
                    <RightArrow />
                </View>
            </TouchableOpacity>

            <TouchableOpacity 
                style={styles.optionViewStyle}
            >
                <View style={styles.iconViewStyle}>
                    <View style={{marginHorizontal: 10}}>
                        <HelpIcon width={icon_width} height={icon_height} />
                    </View>

                    <TextComponent
                        text="Help & Support"
                        size={text_size}
                        bold
                        style={[styles.headingStyle]}
                    />
                </View>

                <View style={styles.arrowViewStyle}>
                    <RightArrow />
                </View>
            </TouchableOpacity>

            <TouchableOpacity 
                style={styles.optionViewStyle}
            >
                <View style={styles.iconViewStyle}>
                    <View style={{marginHorizontal: 10}}>
                        <LogoutIcon width={20} height={20}/>
                    </View>

                    <TextComponent
                        text="Logout"
                        size={16}
                        bold
                        style={[styles.headingStyle]}
                    />
                </View>

                <View style={styles.arrowViewStyle}>
                    <RightArrow />
                </View>
            </TouchableOpacity>
        </View>
    )
}

export default function UserProfile() {
    

    const pathname = usePathname();
    const isActive = pathname === "/userProfile";

    return (
        <TabScreenWrapper isActive={isActive} slideDirection="right">
            <HeaderComponent title="Profile" edit />
            <ScrollView style={styles.scrollContainer}>
                <UserCard />
                <OptionsCard />
            </ScrollView>
        </TabScreenWrapper>
    )
}