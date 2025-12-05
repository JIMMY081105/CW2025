package com.comp2042.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChinaStageDescriptionProvider {

    public static final class ChinaStage {
        private final String name;
        private final String backgroundResource;
        private final String description;

        public ChinaStage(String name, String backgroundResource, String description) {
            this.name = name;
            this.backgroundResource = backgroundResource;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getBackgroundResource() {
            return backgroundResource;
        }

        public String getDescription() {
            return description;
        }
    }

    private static final List<ChinaStage> STAGES = createStages();

    private ChinaStageDescriptionProvider() {
    }

    public static List<ChinaStage> getStages() {
        return STAGES;
    }

    private static List<ChinaStage> createStages() {
        List<ChinaStage> stages = new ArrayList<>();
        stages.add(new ChinaStage("Stage 1 - Si Chuan", "China/1.jpg", "Sichuan is a southwestern province famous for spicy food that makes your mouth tingle with numbing pepper. It is also the home of giant pandas, with large reserves where people can visit and learn about them. The landscape includes mountains, rivers, and stunning places like Jiuzhaigou with colorful lakes. Sichuan feels lively and full of flavor - both in nature and in its culture."));
        stages.add(new ChinaStage("Stage 2 - Shan Dong", "China/2.jpg", "Shandong is a coastal province in eastern China, known as the birthplace of Confucius. It is home to Mount Tai, a historic mountain important in Chinese culture. Cities like Qingdao are known for beaches and well-known local beer. Shandong has a mix of history, culture, and seaside living."));
        stages.add(new ChinaStage("Stage 3 - Zhe Jiang", "China/3.jpg", "Zhejiang is a coastal province known for strong business spirit and fast-growing companies. Its many islands create a unique ocean lifestyle found in few other places in China. Ancient water towns with canals and stone bridges give it a special charm. Zhejiang stands out by blending economic success with cultural beauty."));
        stages.add(new ChinaStage("Stage 4 - Bei Jing ", "China/4.jpg", "Beijing is China s capital and a city where ancient history stands right beside modern skyscrapers. It is home to landmarks like the Forbidden City and the Great Wall nearby - sights you cannot find anywhere else. People come here for art, technology, and big national events. Beijing feels powerful and cultural, all at the same time."));
        stages.add(new ChinaStage("Stage 5 - Hu Bei", "China/5.jpg", "Hubei is a province in central China shaped by the Yangtze River and its many lakes. Its capital, Wuhan, is famous for cherry blossoms in spring, turning parks and universities into pink tunnels of flowers. The region has a strong education scene, tasty hot dry noodles, and important transport connections. With both lively cities and peaceful nature, Hubei has a mix of energy and beauty."));
        stages.add(new ChinaStage("Stage 6 - Chong Qing", "China/6.jpg", "Chongqing is a major city in southwest China built among steep hills, so roads and buildings often rise up and down like a giant maze. It is famous for spicy hotpot and nighttime city views glowing above the rivers. The city s unique mountain city style means elevators, bridges, and cable cars are part of daily life. Chongqing feels bold, energetic, and full of heat - just like its food."));
        stages.add(new ChinaStage("Stage 7 - Ji Lin", "China/7.jpg", "Jilin is a northeastern province known for snowy winters and magical rime ice that covers trees along the Songhua River. It has rich forests and wildlife, making it an important home for animals like the Siberian tiger. In winter, people enjoy skiing and ice festivals. Jilin blends nature, cold-weather fun, and a strong northern culture."));
        stages.add(new ChinaStage("Stage 8 - Jiang Su", "China/8.jpg", "Jiangsu is a wealthy eastern Chinese province known for its smooth canals and elegant gardens. Many of its towns grew around waterways, giving daily life a calm river atmosphere. It has a strong economy, especially in tech and manufacturing, making it one of China s most developed regions. Jiangsu brings together culture, business, and peaceful scenery in a special way."));
        stages.add(new ChinaStage("Stage 9 - An Hui", "China/9.jpg", "Anhui is a province in eastern China known for its misty mountains and old villages with white walls and black roofs. Huangshan, one of China s most famous mountains, rises there with floating clouds and strange pine trees. The province is also known for Huizhou culture - beautiful calligraphy, tea, and historic architecture. Anhui feels peaceful and artistic, with scenery that looks like a classic painting."));
        stages.add(new ChinaStage("Stage 10 - He Nan", "China/10.jpg", "Henan is a central Chinese province often called one of the birthplaces of Chinese civilization. It has ancient cities and famous sites like the Shaolin Temple, where kung fu legends began. The Yellow River runs through it, shaping its long history. Henan stands out for its deep cultural roots and powerful historical stories."));
        stages.add(new ChinaStage("Stage 11 - Hei Long Jiang", "China/11.jpg", "Heilongjiang is China s northernmost province, known for freezing winters and huge snow festivals. Its capital, Harbin, is famous for Russian-style buildings and incredible ice sculptures that glow at night. The province has vast forests and wildlife, including rare animals like the Siberian tiger. Heilongjiang stands out for its strong winter culture and big, snowy adventures."));
        stages.add(new ChinaStage("Stage 12 - Hong Kong", "China/12.jpg", "Hong Kong is a vibrant coastal city known for its tall skyline and busy harbor. It blends Eastern and Western influences, so you will find dim sum restaurants next to neon-lit shopping streets. It is also home to one of Asia s top universities, giving the city a reputation for strong education and bright academic minds. Nature is close too - mountains and islands are just a short ride away. Hong Kong stands out for its fast energy, unique culture, beautiful sea views, and academic excellence."));
        stages.add(new ChinaStage("Stage 13 - Nei Mongol", "China/13.jpg", "Inner Mongolia is a northern region of China known for its wide grasslands and strong traditional Mongolian culture. Many people there enjoy horseback riding and celebrate colorful festivals. It also has large deserts, like the Gobi, with unique landscapes and wildlife. Inner Mongolia blends modern development with a lifestyle that is closely connected to nature."));
        stages.add(new ChinaStage("Stage 14 - Fujian", "China/14.jpg", "Fujian is a southeastern coastal province known for its tea-growing mountains and strong sea traditions. Unique round earthen houses called tulou can be found in its countryside, built by communities living together like a fortress. The province has beautiful coastlines and islands facing Taiwan. Fujian stands out for its mix of mountain tea culture, ocean life, and distinct architecture."));
        stages.add(new ChinaStage("Stage 15 - Shang Hai", "China/15.jpg", "Shanghai is a major coastal city known for its futuristic skyline along the Huangpu River. It mixes modern style with history - from towering skyscrapers to old streets in the Bund. The city is also a global financial hub where ideas, fashion, and business move fast. Shanghai stands out for its energy, international vibe, and bright nights that feel like they never end."));
        stages.add(new ChinaStage("Stage 16 - Xin Jiang", "China/16.jpg", "Xinjiang is a vast region in northwest China known for deserts, snowy mountains, and long ancient routes of the Silk Road. It is home to many cultures, especially the Muslim Uyghur community, whose music, dance, and foods like lamb kebabs and naan are part of daily life. The region s mosques and traditional bazaars show its unique cultural identity. Xinjiang feels adventurous and full of stories from different peoples meeting over centuries."));
        stages.add(new ChinaStage("Stage 17 - Tian Jing", "China/17.jpg", "Tianjin is a major port city in northern China, known for its riverside European-style buildings left from history. It has tasty local snacks like goubuli steamed buns that people line up to try. The city developed strong aerospace and industrial technology, helping drive China s modern industry. Tianjin stands out with its mix of old foreign influences, Chinese culture, and strong innovation."));
        stages.add(new ChinaStage("Stage 18 - Shaan Xi", "China/18.jpg", "Shaanxi is a central Chinese province known for Xi an, the ancient capital where the Terracotta Army guards the resting place of China s first emperor. The province sits at a key starting point of the historic Silk Road, connecting China to distant lands. Its cuisine, especially chewy biangbiang noodles, has a bold and hearty flavor. Shaanxi stands out for deep history, strong cultural roots, and food you will not forget."));
        stages.add(new ChinaStage("Stage 19 - Hai Nan", "China/19.jpg", "Hainan is China s southern island province, known for its warm beaches, palm trees, and blue ocean. It is a popular place for vacations, surfing, and fresh tropical fruits. The island also has volcanic parks and rainforests that show a different side of nature. With sunshine, nature, and island culture, Hainan feels like China s tropical getaway."));
        stages.add(new ChinaStage("Stage 20 - Shan Xi", "China/20.jpg", "Shanxi is a northern Chinese province known for its ancient architecture, including the well-preserved old city of Pingyao. It has deep coal resources, which helped shape its industry and economy. The province is famous for hand-pulled noodles and strong vinegar that locals add to many dishes. Shanxi stands out for its mix of hard-working industrial life and rich historical heritage."));
        stages.add(new ChinaStage("Stage 21 - Yunnan", "China/21.jpg", "Yunnan is a southwestern Chinese province known for its huge mix of ethnic cultures, each with its own festivals, clothes, and foods. Its landscapes range from snow-capped mountains to tropical rainforests, making every area feel different. The region grows some of China s best tea, like Pu er. Yunnan stands out for its incredible diversity in nature, people, and flavors."));
        stages.add(new ChinaStage("Stage 22 - He Bei", "China/22.jpg", "Hebei is a northern province that wraps around Beijing and Tianjin, connecting mountains, plains, and the Bohai Sea. It includes parts of the Great Wall, where you can hike along ancient stones with wide views. The province has a long history of martial arts and strong northern culture. Hebei stands out as a place balancing tradition, industry, and key locations near China s capital."));
        stages.add(new ChinaStage("Stage 23 - Tai Bei", "China/23.jpg", "Taipei is a lively city known for night markets filled with tasty street food like bubble tea and crispy chicken. Modern sights like Taipei 101 stand beside peaceful temples and old streets. The city is surrounded by green mountains, so hiking and hot springs are close to everyday life. Taipei stands out for its friendly atmosphere, great snacks, and fun mix of modern and traditional style."));
        stages.add(new ChinaStage("Stage 24 - Liao Ning", "China/24.jpg", "Liaoning is a northeastern province known for its strong industry, shipbuilding, and important ports along the Yellow Sea. It has rich history - including old Qing Dynasty sites where emperors once lived before moving to Beijing. Winters are cold, but people enjoy hot springs and warm comfort foods like barbecue and dumplings. Liaoning stands out for its mix of industrial power, northern culture, and historical heritage."));
        stages.add(new ChinaStage("Stage 25 - Guang Xi", "China/25.jpg", "Guangxi is a southern region of China famous for its amazing karst mountains and rivers that look like scenes from a painting. It is home to many ethnic groups, especially the Zhuang people, with colorful festivals and music. Popular places like Guilin and Yangshuo attract travelers who love nature and adventure. Guangxi stands out for its beautiful landscapes and diverse cultural traditions."));
        stages.add(new ChinaStage("Stage 26 - Xi Zang", "China/26.jpg", "Xizang is a high-altitude region in southwest China, often called the Roof of the World because of the Himalayas. It is known for Tibetan culture with prayer flags, monasteries, and unique festivals. The breathtaking scenery includes snowy mountains, clear lakes, and wide grasslands. Xizang stands out for its spiritual atmosphere and majestic natural beauty."));
        stages.add(new ChinaStage("Stage 27 - Guang Zhou", "China/27.jpg", "Guangzhou is a major southern city known for its Cantonese culture and delicious dim sum. Its long history of trade made it one of China s earliest international ports, so the city feels open and global. The Pearl River lights up at night with colorful city views. Guangzhou stands out for its food, fast development, and lively modern energy."));
        stages.add(new ChinaStage("Stage 28 - Gui Zhou", "China/28.jpg", "Guizhou is a southwestern province known for its dramatic mountains, waterfalls, and cool climate. Many ethnic groups live here, especially the Miao and Dong people, with unique clothing, music, and wooden village architecture. The famous Huangguoshu Waterfall shows off the province s powerful nature. Guizhou stands out for its rich cultural traditions and hidden scenic gems."));
        stages.add(new ChinaStage("Stage 29 - Hu Nan", "China/29.jpg", "Hunan is a central Chinese province famous for extremely spicy food that makes even chili lovers sweat. It has stunning landscapes like Zhangjiajie, with tall stone pillars that look like floating mountains. The region is also known for its lively culture and being the birthplace of several important historical figures. Hunan stands out for strong flavors, dramatic nature, and bold local spirit."));
        stages.add(new ChinaStage("Stage 30 - Jiang Xi", "China/30.jpg", "Jiangxi is a southeastern province known for its green mountains and calm lakes, especially the famous Poyang Lake full of migrating birds. It has a long history of porcelain making in Jingdezhen, often called the Porcelain Capital of the World. The region also grows high-quality tea in misty villages. Jiangxi stands out for its peaceful nature and traditional craftsmanship."));

        return Collections.unmodifiableList(stages);
    }
}
